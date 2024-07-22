package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterWithRecyclerBinding
import ru.practicum.android.diploma.filter.domain.model.AreaDetailsFilterItem
import ru.practicum.android.diploma.filter.presentation.state.AreaFilterState
import ru.practicum.android.diploma.filter.presentation.viewmodel.RegionFilterViewModel

class FilterRegionFragment : Fragment() {
    private var _binding: FragmentFilterWithRecyclerBinding? = null
    private val binding get() = _binding!!

    private val viewModelRegion: RegionFilterViewModel by viewModel<RegionFilterViewModel>()

    private lateinit var adapter: FilterCountryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFilterWithRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchTitle.text = requireContext().getString(R.string.choice_region)
        viewVisibility()

        // новый код
        binding.backButtonFilterWithRecycler.setOnClickListener { findNavController().navigateUp() }
        binding.filterApplyButton.setOnClickListener {
            findNavController().navigateUp()
            // Добавить запись настроек фильтра в Shared Prefs
        }

        binding.backButtonFilterWithRecycler.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.recyclerViewFilter.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.filterInputET.doOnTextChanged { text, _, _, _ ->
            val iconResId = if (text.isNullOrEmpty()) R.drawable.icon_search else R.drawable.icon_cross
            binding.filterInputIcon.background = requireActivity().getDrawable(iconResId)
            if (!text.isNullOrEmpty()) {
                viewModelRegion.searchRegionByName(text.toString())
            } else {
                viewModelRegion.getOriginalListBeforeSearching()
            }
        }

        binding.filterInputIcon.setOnClickListener {
            viewModelRegion.getOriginalListBeforeSearching()
            binding.filterInputET.setText("")
            val imm: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.filterInputET.windowToken, 0)
        }

        viewModelRegion.stateLiveDataRegion.observe(viewLifecycleOwner) {
            renderStateLiveData(it)
        }
    }

    private fun renderStateLiveData(state: AreaFilterState) {
        binding.searchPlaceholderImage.isVisible = state is AreaFilterState.Error || state is AreaFilterState.Empty
        binding.searchPlaceholderText.isVisible = state is AreaFilterState.Error || state is AreaFilterState.Empty
        binding.loadingProgressBar.isVisible = state is AreaFilterState.Loading
        binding.recyclerViewFilter.isVisible = state is AreaFilterState.AreaContent

        when (state) {
            is AreaFilterState.AreaContent -> {
                adapter = FilterCountryAdapter(state.listOfAreas, ::clickListenerFun)
                binding.recyclerViewFilter.adapter = adapter
                adapter.updateList(state.listOfAreas)
            }

            is AreaFilterState.Error -> {
                binding.searchPlaceholderText.text =
                    requireContext().getString(R.string.failed_to_receive_region_list)
                binding.searchPlaceholderImage.setImageResource(R.drawable.picture_flying_men)
            }

            is AreaFilterState.Empty -> {
                binding.searchPlaceholderText.text =
                    requireContext().getString(R.string.no_such_region)
                binding.searchPlaceholderImage.setImageResource(R.drawable.picture_angry_cat)
            }

            is AreaFilterState.Loading -> {
                Unit
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickListenerFun(area: AreaDetailsFilterItem) {
        viewModelRegion.saveRegionChoiceToFilter(area)
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun viewVisibility() {
        binding.filterInput.isVisible = true
        binding.filterInputIcon.isVisible = true
        binding.filterInputET.hint = requireActivity().getString(R.string.enter_region)
    }

}
