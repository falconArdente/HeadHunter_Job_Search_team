package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterWithRecyclerBinding
import ru.practicum.android.diploma.filter.domain.model.AreaDetailsFilterItem
import ru.practicum.android.diploma.filter.presentation.state.AreaFilterState
import ru.practicum.android.diploma.filter.presentation.viewmodel.CountryFilterViewModel

class FilterCountryFragment : Fragment() {
    private var _binding: FragmentFilterWithRecyclerBinding? = null
    private val binding get() = _binding!!

    private val viewModelCountry: CountryFilterViewModel by viewModel<CountryFilterViewModel>()

    private var adapter = FilterCountryAdapter(emptyList(), ::clickListenerFun)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFilterWithRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchTitle.text = requireContext().getString(R.string.choice_country)
        setViewVisibility()

        binding.backButtonFilterWithRecycler.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        viewModelCountry.stateLiveDataCountry.observe(viewLifecycleOwner) {
            binding.searchPlaceholderImage.isVisible = it is AreaFilterState.Error
            binding.searchPlaceholderText.isVisible = it is AreaFilterState.Error
            binding.loadingProgressBar.isVisible = it is AreaFilterState.Loading
            binding.recyclerViewFilter.isVisible = it is AreaFilterState.AreaContent

            when (it) {
                is AreaFilterState.AreaContent -> {
                    adapter.updateList(it.listOfAreas)
                    binding.recyclerViewFilter.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    binding.recyclerViewFilter.adapter = adapter
                }

                is AreaFilterState.Error, is AreaFilterState.Empty -> {
                    binding.searchPlaceholderText.text = requireContext().getString(R.string.server_error)
                }

                is AreaFilterState.Loading -> Unit
            }
        }

        viewModelCountry.backEvent.observe(viewLifecycleOwner) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickListenerFun(country: AreaDetailsFilterItem) {
        viewModelCountry.saveCountryChoiceToFilter(country)
    }

    private fun setViewVisibility() {
        binding.filterApplyButton.isVisible = false
        binding.filterInput.isVisible = false
        binding.filterInputIcon.isVisible = false
    }
}
