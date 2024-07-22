package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterSettingsBinding
import ru.practicum.android.diploma.filter.presentation.state.FilterSettingsState
import ru.practicum.android.diploma.filter.presentation.viewmodel.FilterSettingsViewModel

class FilterSettingsFragment : Fragment() {
    private var _binding: FragmentFilterSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FilterSettingsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadConfiguredFilterSettings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.filterSalaryInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.filterSalaryInputTitle.setTextColor(requireActivity().getColor(R.color.Blue))
            } else if (binding.filterSalaryInput.text.isEmpty()) {
                binding.filterSalaryInputTitle.setTextColor(requireActivity().getColor(R.color.Gray_OR_White))
            } else {
                binding.filterSalaryInputTitle.setTextColor(requireActivity().getColor(R.color.Black))
            }
        }

        viewModel.getState().observe(viewLifecycleOwner) { state ->
            render(state)
        }

        binding.filterWorkPlaceInactive.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_filterPlaceToWorkFragment)
        }

        binding.filterIndustryInactive.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_filterIndustryFragment)
        }
        /* пока не понял зачем они нужны
        binding.filterWorkPlaceCross.setOnClickListener {
            viewModel.changeWorkPlace(newWorkPlace = "")
        }

        binding.filterIndustryCross.setOnClickListener {
            viewModel.changeIndustry(newIndustry = "")
        }
        */
        binding.filterSalaryInput.doOnTextChanged { text, _, _, _ ->
            if (text?.isNotEmpty() == true) {
                viewModel.changeSalary(
                    newSalary = binding.filterSalaryInput.text.toString()
                )
            }
        }

        binding.filterDontShowWithoutSalaryCheckBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.changeHideNoSalary(noSalary = isChecked)
        }

        binding.filterApplyButton.setOnClickListener {
            viewModel.saveFilterSettings()
            findNavController().navigateUp()

        }

        viewModel.loadSavedFilterSettings()
    }

    private fun render(state: FilterSettingsState) {
        when (state) {
            is FilterSettingsState.Filter -> {
                binding.filterDontShowWithoutSalaryCheckBox.isChecked = state.filter.hideNoSalaryItems
                if (state.filter.expectedSalary.isNullOrEmpty()) {
                    binding.filterSalaryInput.text.clear()
                } else {
                    binding.filterSalaryInput.setText(state.filter.expectedSalary.toInt())
                }

                if (state.filter.area?.areaName.isNullOrEmpty()) {
                    binding.filterWorkPlaceInactive.visibility = View.VISIBLE
                    binding.filterWorkPlaceActive.visibility = View.GONE
                } else {
                    binding.filterWorkPlaceInactive.visibility = View.GONE
                    binding.filterWorkPlaceActive.visibility = View.VISIBLE
                    binding.filterWorkPlaceValue.text = state.filter.area!!.areaName
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

}
