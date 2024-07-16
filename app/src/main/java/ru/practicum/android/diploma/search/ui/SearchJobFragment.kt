package ru.practicum.android.diploma.search.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchJobBinding
import ru.practicum.android.diploma.details.ui.JobDetailsFragment
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.presentation.state.SearchFragmentState
import ru.practicum.android.diploma.search.presentation.viewmodel.SearchViewModel
import ru.practicum.android.diploma.utils.hideKeyboard
import ru.practicum.android.diploma.utils.showKeyboard

class SearchJobFragment : Fragment() {
    private var _binding: FragmentSearchJobBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: VacancyAdapter
    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHolderInit()

        binding.searchFilterButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchJobFragment_to_filterSettingsFragment)
        }

        binding.searchInput.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                binding.searchInputIcon.background = requireActivity().getDrawable(R.drawable.icon_search)
            } else {
                binding.searchInputIcon.background = requireActivity().getDrawable(R.drawable.icon_cross)
            }
        }

        binding.searchInputIcon.setOnClickListener {
            binding.searchInput.setText("")
        }
        binding.searchInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.searchInput.showKeyboard(requireContext())
                showView()
            }
        }
        binding.searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.searchInput.hideKeyboard(requireContext())
            }
            false
        }

        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                showView()
            }
            override fun afterTextChanged(p0: Editable?) {
                showView()
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrEmpty()) {
                    viewModel.searchWithDebounce(p0.toString())
                } else if (p0.isNullOrEmpty()) {
                    adapter.updateList(emptyList())
                    viewModel.updateState(SearchFragmentState.NoTextInInputEditText)
                    showView()
                }
            }
        })
    }

    private fun showView() {
        viewModel.fragmentStateLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is SearchFragmentState.SearchVacancy -> {
                    adapter.updateList(it.searchVacancy)
                    searchVacancyView()
                }

                is SearchFragmentState.Loading -> {
                    loadingView()
                }

                is SearchFragmentState.NoResult -> {
                    noResultsView()
                }

                is SearchFragmentState.ServerError -> {
                    serverErrorView()
                }

                is SearchFragmentState.NoTextInInputEditText -> {
                    noTextView()
                }

                else -> {}
            }
        }
    }

    private fun searchVacancyView() {
        binding.recyclerViewSearch.visibility = View.VISIBLE
        binding.searchPlaceholderImage.visibility = View.GONE
        binding.noResultsSearchInclude.root.visibility = View.GONE
        binding.serverErrorInclude.root.visibility = View.GONE
        binding.searchProgressBar.visibility = View.GONE
    }

    private fun loadingView() {
        binding.recyclerViewSearch.visibility = View.GONE
        binding.searchPlaceholderImage.visibility = View.GONE
        binding.searchProgressBar.visibility = View.VISIBLE
        binding.noResultsSearchInclude.root.visibility = View.GONE
        binding.serverErrorInclude.root.visibility = View.GONE
    }

    private fun noTextView() {
        binding.recyclerViewSearch.visibility = View.GONE
        binding.searchPlaceholderImage.visibility = View.VISIBLE
        binding.searchProgressBar.visibility = View.GONE
        binding.noResultsSearchInclude.root.visibility = View.GONE
        binding.serverErrorInclude.root.visibility = View.GONE
    }

    private fun noResultsView() {
        binding.noResultsSearchInclude.root.visibility = View.VISIBLE
        binding.serverErrorInclude.root.visibility = View.GONE
        binding.searchProgressBar.visibility = View.GONE
        binding.recyclerViewSearch.visibility = View.GONE
        binding.searchPlaceholderImage.visibility = View.GONE
    }

    private fun serverErrorView() {
        binding.noResultsSearchInclude.root.visibility = View.GONE
        binding.serverErrorInclude.root.visibility = View.VISIBLE
        binding.searchProgressBar.visibility = View.GONE
        binding.recyclerViewSearch.visibility = View.GONE
        binding.searchPlaceholderImage.visibility = View.GONE
    }

    private fun clickListenerFun() = object : SearchRecyclerViewEvent {
        override fun onItemClick(vacancy: Vacancy) {
            if (viewModel.clickDebounce()) {
                findNavController().navigate(
                    R.id.action_searchJobFragment_to_jobDetailsFragment,
                    JobDetailsFragment.createArgs(vacancy.id)
                )
            }
        }
    }
    private fun viewHolderInit() {
        adapter = VacancyAdapter(emptyList(), clickListenerFun())
        binding.recyclerViewSearch.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewSearch.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
