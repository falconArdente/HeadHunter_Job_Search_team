package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterWithRecyclerBinding
import ru.practicum.android.diploma.filter.domain.model.Area

class FilterRegionFragment : Fragment() {
    private var _binding: FragmentFilterWithRecyclerBinding? = null
    private val binding get() = _binding!!
    val area = Area(null, "оо", "имя", "порп")
    val list = listOf(area)
    private val adapter = FilterRegionAdapter(list, ::clickListenerFun)
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

    private fun clickListenerFun(area: Area) {
        //  реализовать клик
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
            requireActivity().getString(R.string.enter_region)
        binding.filterInputET.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                binding.filterInputIcon.background = requireActivity().getDrawable(R.drawable.icon_search)
            } else {
                binding.filterInputIcon.background = requireActivity().getDrawable(R.drawable.icon_cross)
            }
        }
    }
}
