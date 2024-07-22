package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.presentation.state.CountryFilterState
import ru.practicum.android.diploma.filter.presentation.viewmodel.RegionFilterViewModel

class FilterRegionFragment : FilterCountryFragment() {

    override val viewModel: RegionFilterViewModel by viewModel<RegionFilterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchTitle.text = requireContext().getString(R.string.choice_region)
        viewVisibility()

        binding.backButtonFilterWithRecycler.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            binding.searchPlaceholderImage.isVisible = it is CountryFilterState.Error
            binding.searchPlaceholderText.isVisible = it is CountryFilterState.Error
            binding.loadingProgressBar.isVisible = it is CountryFilterState.Loading
            binding.recyclerViewFilter.isVisible = it is CountryFilterState.CountryContent

            when (it) {
                is CountryFilterState.CountryContent -> {
                    adapter = FilterCountryAdapter(it.listOfCountries, ::clickListenerFun)
                    viewHolderInit()
                }
                is CountryFilterState.Error -> {
                    binding.searchPlaceholderText.text = requireContext().getString(R.string.server_error)
                }

                CountryFilterState.Loading -> Unit
            }
        }
    }

    override fun clickListenerFun(area: Area) {
        viewModel.saveRegionChoiceToFilter(area)
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun viewVisibility() {
        binding.filterInput.isVisible = true
        binding.filterInputIcon.isVisible = true
        binding.filterInputET.hint = requireActivity().getString(R.string.enter_region)
        binding.filterInputET.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                binding.filterInputIcon.background = requireActivity().getDrawable(R.drawable.icon_search)
            } else {
                binding.filterInputIcon.background = requireActivity().getDrawable(R.drawable.icon_cross)
            }
        }
    }

}
