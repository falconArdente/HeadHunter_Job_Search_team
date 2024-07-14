package ru.practicum.android.diploma.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchJobBinding
import ru.practicum.android.diploma.details.ui.JobDetailsFragment

class SearchJobFragment : Fragment() {
    private var _binding: FragmentSearchJobBinding? = null
    private val binding get() = _binding!!
    private var suggestionsAdapter: VacancyPositionSuggestsAdapter? = null

    private val viewModel by viewModel<SearchJobViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchFilterButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchJobFragment_to_filterSettingsFragment)
        }

        binding.searchInput.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                binding.searchInputIcon.background = requireActivity().getDrawable(R.drawable.icon_search)
            } else {
                binding.searchInputIcon.background = requireActivity().getDrawable(R.drawable.icon_cross)
                viewModel.getSuggestionsForSearch(text.toString())
            }
        }

        binding.searchInputIcon.setOnClickListener {
            binding.searchInput.setText("")
        }

        suggestionsAdapter = VacancyPositionSuggestsAdapter(requireActivity(), binding.searchInput)
        binding.searchInput.setAdapter(suggestionsAdapter)
        viewModel.suggestionsLivaData.observe(viewLifecycleOwner) { renderSuggestions(it) }
    }

    private fun renderSuggestions(incomeSuggestions: List<String>) {
        suggestionsAdapter?.applyDataSet(incomeSuggestions)
    }
}
