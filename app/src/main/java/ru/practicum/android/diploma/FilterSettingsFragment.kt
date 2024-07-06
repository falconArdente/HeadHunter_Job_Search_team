package ru.practicum.android.diploma

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.practicum.android.diploma.databinding.FragmentFilterSettingsBinding

class FilterSettingsFragment : Fragment() {
    private var _binding: FragmentFilterSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.country.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_fltrCountryFragment)
        }
        binding.department.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_fltrDepartmentFragment)
        }
        binding.region.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_filterRegionFragment)
        }
        binding.work.setOnClickListener {
            findNavController().navigate(R.id.action_filterSettingsFragment_to_filterPlaceToWorkFragment)
        }
    }

}
