package ru.practicum.android.diploma.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchJobBinding

class SearchJobFragment : Fragment() {
    private var _binding: FragmentSearchJobBinding? = null
    private val binding get() = _binding!!
    private var simpleAdapter: ArrayAdapter<String>? = null
    private var baseChildAdapter: VacancyPositionSuggestsAdapter? = null

    private val viewModel by viewModel<SearchJobViewModel>()
    private val array = mutableListOf("aone", "a2one", "a3one", "a4one")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
                //viewModel.getSuggestionsForSearch(text.toString())
            }
        }

        binding.searchInputIcon.setOnClickListener {
            binding.searchInput.setText("")
        }
        baseChildAdapter =
            VacancyPositionSuggestsAdapter(
                requireActivity(),binding.searchInput
            )
        simpleAdapter = ArrayAdapter<String>(requireActivity(), R.layout.suggestion_view_holder, array)
        simpleAdapter!!.setNotifyOnChange(true)



        binding.searchInput.setAdapter(baseChildAdapter)

        lifecycleScope.launch {
            var index = 0
            while (index < 30) {
                baseChildAdapter!!.add("asd$index")
                //baseChildAdapter!!.notifyDataSetChanged()

                index++
                delay(1000L)

            }
        }
    }

    private fun renderSuggestions(incomeSuggestions: List<String>) {

    }
}


