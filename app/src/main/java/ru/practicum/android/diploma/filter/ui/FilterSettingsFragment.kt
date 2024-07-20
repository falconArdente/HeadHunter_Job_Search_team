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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.filterSalaryInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.filterSalaryInputTitle.setTextColor(requireActivity().getColor(R.color.Blue))
            } else {
                if (binding.filterSalaryInput.text.isEmpty())
                    binding.filterSalaryInputTitle.setTextColor(requireActivity().getColor(R.color.Gray_OR_White))
                else {
                    binding.filterSalaryInputTitle.setTextColor(requireActivity().getColor(R.color.Black))
                }
            }
        }

        viewModel.getState().observe(viewLifecycleOwner) { state ->
            render(state)

        }

        binding.filterWorkPlaceInactive.setOnClickListener {
            viewModel.changeWorkPlace(newWorkPlace = "Москва, Россия")
        }

        binding.filterIndustryInactive.setOnClickListener {
            viewModel.changeIndustry(newIndustry = "IT")
        }

        binding.filterWorkPlaceCross.setOnClickListener {
            viewModel.changeWorkPlace(newWorkPlace = "")
        }

        binding.filterIndustryCross.setOnClickListener {
            viewModel.changeIndustry(newIndustry = "")
        }

        binding.filterSalaryInput.doOnTextChanged { text, _, _, _ ->
            viewModel.changeSalary(newSalary = text.toString())
        }

        binding.filterDontShowWithoutSalaryCheckBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.changeDontShowWithoutSalary(newDontShow = isChecked)
        }

    }

    private fun render(state: FilterSettingsState) {
        if (state.workPlace.isEmpty()) {
            binding.filterWorkPlaceInactive.visibility = View.VISIBLE
            binding.filterWorkPlaceActive.visibility = View.GONE
        } else {
            binding.filterWorkPlaceInactive.visibility = View.GONE
            binding.filterWorkPlaceActive.visibility = View.VISIBLE
        }

        if (state.industry.isEmpty()) {
            binding.filterIndustryInactive.visibility = View.VISIBLE
            binding.filterIndustryActive.visibility = View.GONE
        } else {
            binding.filterIndustryInactive.visibility = View.GONE
            binding.filterIndustryActive.visibility = View.VISIBLE
        }
    }

}
