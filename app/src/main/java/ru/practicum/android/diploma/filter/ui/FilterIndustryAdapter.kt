package ru.practicum.android.diploma.filter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.FilterWithRecyclerItemBinding
import ru.practicum.android.diploma.filter.domain.model.Industry

class FilterIndustryAdapter(
    private var listIndustry: List<Industry>,
    private var clickListener: (industry: Industry) -> Unit
) : RecyclerView.Adapter<FilterIndustryAdapter.IndustryFilterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndustryFilterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FilterWithRecyclerItemBinding.inflate(inflater, parent, false)
        return IndustryFilterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: IndustryFilterViewHolder,
        position: Int
    ) {
        holder.bind(listIndustry[position])
        holder.itemView.setOnClickListener {
            clickListener(listIndustry[position])
        }
    }

    fun updateList(updatedVacancyList: List<Industry>) {
        listIndustry = updatedVacancyList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listIndustry.size
    }

    inner class IndustryFilterViewHolder(
        private val binding: FilterWithRecyclerItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Industry) {
            binding.textViewFilterItem.text = item.name
            binding.switcherFilter.visibility = View.VISIBLE
            binding.arrowFilterMini.visibility = View.GONE
        }
    }
}
