package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterSettingsBinding

class FilterSettingsFragment : Fragment() {
    private var _binding: FragmentFilterSettingsBinding? = null
    private val binding get() = _binding!!
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
        binding.country.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_filterCountryFragment)
        }
        binding.department.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_filterDepartmentFragment)
        }
        binding.region.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_filterRegionFragment)
        }
        binding.work.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_filterPlaceToWorkFragment)
        }
    }

}
