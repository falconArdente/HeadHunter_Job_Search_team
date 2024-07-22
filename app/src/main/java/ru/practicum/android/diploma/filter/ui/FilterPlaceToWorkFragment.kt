package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterSettingsBinding
import ru.practicum.android.diploma.databinding.FragmentFilterWithRecyclerBinding
import ru.practicum.android.diploma.filter.domain.model.Country

class FilterPlaceToWorkFragment : Fragment() {
    private var _binding: FragmentFilterSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFilterSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateFragment()

        binding.filterArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.filterWorkPlaceInactive.setOnClickListener {
            findNavController().navigate(R.id.action_filterPlaceToWorkFragment_to_filterCountryFragment)
        }

        binding.filterIndustryInactive.setOnClickListener {
            findNavController().navigate(R.id.action_filterPlaceToWorkFragment_to_filterRegionFragment)
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
