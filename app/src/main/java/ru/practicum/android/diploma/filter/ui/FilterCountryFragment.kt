package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterWithRecyclerBinding
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.presentation.state.CountryFilterState
import ru.practicum.android.diploma.filter.presentation.viewmodel.CountryFilterViewModel

open class FilterCountryFragment : Fragment() {
    private var _binding: FragmentFilterWithRecyclerBinding? = null
    protected val binding get() = _binding!!

    protected open val viewModel: CountryFilterViewModel by viewModel<CountryFilterViewModel>()

    protected open lateinit var adapter: FilterCountryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFilterWithRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchTitle.text = requireContext().getString(R.string.choice_country)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun clickListenerFun(country: Area) {
        viewModel.saveCountryChoiceToFilter(country)
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    protected open fun viewVisibility() {
        binding.filterInput.isVisible = false
        binding.filterInputIcon.isVisible = false
    }

    protected open fun viewHolderInit() {
        binding.recyclerViewFilter.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewFilter.adapter = adapter
    }
}
