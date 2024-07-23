package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterSettingsBinding
import ru.practicum.android.diploma.filter.presentation.state.PlaceToWorkFilterState
import ru.practicum.android.diploma.filter.presentation.viewmodel.PlaceToWorkFilterViewModel

class FilterPlaceToWorkFragment : Fragment() {
    private var _binding: FragmentFilterSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlaceToWorkFilterViewModel by viewModel<PlaceToWorkFilterViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFilterSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateFragment()

        viewModel.getCurrentFilterAreaParameters()

        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            renderFilterFields(it)
        }

        binding.filterArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.filterWorkPlaceInactive.setOnClickListener {
            findNavController().navigate(R.id.action_filterPlaceToWorkFragment_to_filterCountryFragment)
        }

        binding.filterWorkPlaceActive.setOnClickListener {
            findNavController().navigate(R.id.action_filterPlaceToWorkFragment_to_filterCountryFragment)
        }

        binding.filterIndustryInactive.setOnClickListener {
            findNavController().navigate(R.id.action_filterPlaceToWorkFragment_to_filterRegionFragment)
        }

        binding.filterIndustryActive.setOnClickListener {
            findNavController().navigate(R.id.action_filterPlaceToWorkFragment_to_filterRegionFragment)
        }

        binding.filterWorkPlaceCross.setOnClickListener { viewModel.clearCountry() }
        binding.filterIndustryCross.setOnClickListener { viewModel.clearArea() }

        binding.filterApplyButton.setOnClickListener {
            viewModel.saveFilterAreaParameters()
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun renderFilterFields(filterParamsState: PlaceToWorkFilterState?) {
        if (filterParamsState is PlaceToWorkFilterState.AreaFilter) {
            if (!filterParamsState.countryName.isNullOrEmpty()) {
                binding.filterWorkPlaceInactive.isVisible = false
                binding.filterWorkPlaceActive.isVisible = true
                binding.filterWorkPlaceTitle.text = requireActivity().getString(R.string.country)
                binding.filterWorkPlaceValue.text = filterParamsState.countryName

            } else {
                binding.filterWorkPlaceInactive.isVisible = true
                binding.filterWorkPlaceActive.isVisible = false
            }

            if (!filterParamsState.areaName.isNullOrEmpty()) {
                binding.filterIndustryInactive.isVisible = false
                binding.filterIndustryActive.isVisible = true
                binding.filterIndustryTitle.text = requireActivity().getString(R.string.region)
                binding.filterIndustryValue.text = filterParamsState.areaName
            } else {
                binding.filterIndustryInactive.isVisible = true
                binding.filterIndustryActive.isVisible = false
            }

            if (binding.filterIndustryValue.text != requireActivity().getString(R.string.region) ||
                binding.filterWorkPlaceValue.text != requireActivity().getString(R.string.country)
            ) {
                binding.filterApplyButton.isVisible = true
            } else {
                binding.filterApplyButton.isVisible = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun inflateFragment() {
        binding.filterSalaryInput.visibility = View.GONE
        binding.filterSalaryInputTitle.visibility = View.GONE
        binding.filterSalaryInputBackground.visibility = View.GONE
        binding.filterDontShowWithoutSalaryCheckBox.visibility = View.GONE
        binding.filterDontShowWithoutSalaryTitle.visibility = View.GONE
        binding.filterApplyButton.visibility = View.GONE
        binding.filterResetButton.visibility = View.GONE

        binding.filterTitle.text = requireActivity().getString(R.string.choice_place_to_work)
        binding.filterFirstFilterName.text = requireActivity().getString(R.string.country)
        binding.filterSecondFilterName.text = requireActivity().getString(R.string.region)
    }

}
