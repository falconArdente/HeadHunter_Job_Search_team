package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.practicum.android.diploma.databinding.FragmentFilterWithRecyclerBinding
import ru.practicum.android.diploma.filter.domain.model.Country

class FilterCountryFragment : Fragment() {
    private var _binding: FragmentFilterWithRecyclerBinding? = null
    private val binding get() = _binding!!
    private val country = Country("р", "тестовый лист страна", "роп")
    val list = listOf(country)
    private val adapter = FilterCountryAdapter(list, clickListenerFun())
    // заменить в адаптере на пустой лист потом

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFilterWithRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHolderInit()
        viewVisibility()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickListenerFun() = object : RVEvent {
        override fun onItemClick(country: Country) {
            //   реализовать клик
        }
    }
    private fun viewVisibility(){
        binding.filterInput.visibility=View.GONE
        binding.filterInputIcon.visibility=View.GONE
    }


    private fun viewHolderInit() {
        binding.recyclerViewFilter.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewFilter.adapter = adapter
    }
}
