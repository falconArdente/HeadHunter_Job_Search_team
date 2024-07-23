package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterWithRecyclerBinding
import ru.practicum.android.diploma.filter.presentation.model.IndustryWithCheck
import ru.practicum.android.diploma.filter.presentation.state.FilterIndustryState
import ru.practicum.android.diploma.filter.presentation.viewmodel.FilterIndustryViewModel

class FilterIndustryFragment : Fragment() {
    private var _binding: FragmentFilterWithRecyclerBinding? = null
    private val binding get() = _binding!!
    private val adapter = FilterIndustryAdapter(emptyList(), ::clickListenerFun)
    private val viewModel by viewModel<FilterIndustryViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFilterWithRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHolderInit()
        viewVisibility()

        binding.backButtonFilterWithRecycler.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.filterApplyButton.setOnClickListener {
            viewModel.saveIndustry()
        }

        viewModel.getState().observe(viewLifecycleOwner) { state ->
            render(state)
        }

        viewModel.loadIndustryList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(state: FilterIndustryState) {
        when (state) {
            is FilterIndustryState.SavedFilter -> {
                findNavController().navigateUp()
            }

            is FilterIndustryState.EmptyList -> {
                binding.recyclerViewFilter.isVisible = false
            }

            is FilterIndustryState.LoadedList -> {
                adapter.updateList(state.industry)
                binding.recyclerViewFilter.isVisible = true
            }
        }
    }

    private fun clickListenerFun(industry: IndustryWithCheck) {
        viewModel.saveSelectedIndustry(industry.industry)
    }

    private fun viewHolderInit() {
        binding.recyclerViewFilter.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        binding.recyclerViewFilter.adapter = adapter
    }

    private fun viewVisibility() {
        binding.filterInputET.hint =
            requireActivity().getString(R.string.enter_industry)
        binding.filterInputET.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                binding.filterInputIcon.background = requireActivity().getDrawable(R.drawable.icon_search)
            } else {
                adapter.updateListByFilter(text.toString())
                binding.filterInputIcon.background = requireActivity().getDrawable(R.drawable.icon_cross)
            }
        }
    }
}
