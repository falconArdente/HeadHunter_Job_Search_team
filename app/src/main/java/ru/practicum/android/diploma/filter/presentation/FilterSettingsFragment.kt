package ru.practicum.android.diploma.filter.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterSettingsBinding
import ru.practicum.android.diploma.filter.presentation.state.FilterSettingsState
import ru.practicum.android.diploma.filter.presentation.viewmodel.FilterSettingsViewModel
import ru.practicum.android.diploma.search.ui.SearchRepeatHandler
import ru.practicum.android.diploma.utils.debounce

private const val SALARY_ITEMS_DEBOUNCE_DELAY = 100L

class FilterSettingsFragment : Fragment() {
    private var _binding: FragmentFilterSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FilterSettingsViewModel>()
    private var previousSalaryText = String()
    private var previousCheckBoxValue = false
    private var salaryFieldDebounced: ((String) -> Unit)? = null
    private var checkBoxDebounced: ((Boolean) -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateAllFiltersInfo()
    }

    private fun initiateDebounce() {
        salaryFieldDebounced = debounce(
            SALARY_ITEMS_DEBOUNCE_DELAY, viewLifecycleOwner.lifecycleScope, true
        ) { salaryText ->
            viewModel.changeSalary(salaryText)
        }
        checkBoxDebounced = debounce(
            SALARY_ITEMS_DEBOUNCE_DELAY, viewLifecycleOwner.lifecycleScope, true
        ) { doHideNoSalaryVacs ->
            viewModel.changeHideNoSalary(doHideNoSalaryVacs)
        }
    }

    private fun setClicks() {
        binding.filterArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.filterWorkPlaceInactive.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_filterPlaceToWorkFragment)
        }

        binding.filterIndustryInactive.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_filterIndustryFragment)
        }

        binding.filterWorkPlaceCross.setOnClickListener {
            viewModel.resetRegion()
        }

        binding.filterIndustryCross.setOnClickListener {
            viewModel.resetIndustry()
        }
        binding.filterResetButton.setOnClickListener {
            viewModel.resetFilterSettings()
        }

        binding.filterApplyButton.setOnClickListener {
            viewModel.saveFilterSettings()
            doRepeatBoolSequence()
            findNavController().navigateUp()
        }
        binding.filterSalaryCross.setOnClickListener {
            binding.filterSalaryInput.setText(String())
            salaryFieldDebounced?.invoke(String())
            previousSalaryText = String()
        }
    }

    private fun doRepeatBoolSequence() {
        val repeatHandler = requireActivity()
        if (repeatHandler is SearchRepeatHandler) {
            repeatHandler.setRepeat(true)
        }
    }

    private fun setTextActions() {
        binding.filterSalaryInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                salaryFieldDebounced?.invoke(binding.filterSalaryInput.text.toString())
                val inputManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(binding.filterSalaryInput.windowToken, 0)
            }
            false
        }
        binding.filterSalaryInput.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                binding.filterSalaryCross.visibility = View.GONE
                binding.filterSalaryInputTitle.setTextColor(requireActivity().getColor(R.color.Gray_OR_White))
            } else {
                binding.filterSalaryInputTitle.setTextColor(requireActivity().getColor(R.color.Blue))
                binding.filterSalaryCross.visibility = View.VISIBLE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filterSalaryInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val text = binding.filterSalaryInput.text.toString()
                if (text.isNotEmpty()) {
                    if (text != previousSalaryText) {
                        salaryFieldDebounced?.invoke(text)
                        previousSalaryText = text
                    }
                    binding.filterSalaryInputTitle.setTextColor(requireActivity().getColor(R.color.Black))
                }
            }
        }
        viewModel.getState().observe(viewLifecycleOwner) { state ->
            render(state)
        }
        setClicks()
        setTextActions()
        initiateDebounce()
        binding.filterDontShowWithoutSalaryCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked != previousCheckBoxValue) {
                checkBoxDebounced?.invoke(isChecked)
                previousCheckBoxValue = isChecked
            }
        }
    }

    private fun render(state: FilterSettingsState) {
        when (state) {
            is FilterSettingsState.Data -> {
                binding.filterApplyButton.isVisible = state.isActiveApply
                binding.filterResetButton.isVisible = state.isActiveReset
                binding.filterDontShowWithoutSalaryCheckBox.isChecked = state.filter.hideNoSalaryItems

                if (state.filter.expectedSalary.isNullOrEmpty()) {
                    binding.filterSalaryInput.text.clear()
                } else {
                    binding.filterSalaryInput.setText(state.filter.expectedSalary)
                }

                if (state.filter.country?.countryName.isNullOrEmpty()) {
                    binding.filterWorkPlaceInactive.visibility = View.VISIBLE
                    binding.filterWorkPlaceActive.visibility = View.GONE
                } else {
                    binding.filterWorkPlaceInactive.visibility = View.GONE
                    binding.filterWorkPlaceActive.visibility = View.VISIBLE
                    binding.filterWorkPlaceValue.text = if (state.filter.area?.areaName == null) {
                        state.filter.country?.countryName
                    } else {
                        requireActivity().getString(
                            R.string.filter_region,
                            state.filter.country?.countryName,
                            state.filter.area?.areaName
                        )
                    }
                }

                if (state.filter.industry?.industryName.isNullOrEmpty()) {
                    binding.filterIndustryInactive.visibility = View.VISIBLE
                    binding.filterIndustryActive.visibility = View.GONE
                } else {
                    binding.filterIndustryInactive.visibility = View.GONE
                    binding.filterIndustryActive.visibility = View.VISIBLE
                    binding.filterIndustryValue.text = state.filter.industry!!.industryName
                }
            }

            else -> {
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
