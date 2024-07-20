package ru.practicum.android.diploma.filter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.FilterWithRecyclerItemBinding
import ru.practicum.android.diploma.filter.domain.model.Country

class FilterCountryAdapter(
    private var listCountry: List<Country>,
    private var clickListener: RVEvent
) : RecyclerView.Adapter<FilterCountryAdapter.CountryFilterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryFilterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vacancyViewBinding = FilterWithRecyclerItemBinding.inflate(inflater, parent, false)
        return CountryFilterViewHolder(vacancyViewBinding)
    }

    override fun onBindViewHolder(holder: CountryFilterViewHolder, position: Int) {
        holder.bind(listCountry[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(listCountry[position])
        }
    }

    fun updateList(updatedVacancyList: List<Country>) {
        listCountry = updatedVacancyList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listCountry.size
    }

    inner class CountryFilterViewHolder(private val binding: FilterWithRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Country) {
            binding.textViewFilterItem.text = item.name
            binding.switherFilter.visibility = View.GONE
            binding.arrowFilterMini.visibility = View.VISIBLE
        }
    }
}

interface RVEvent {
    fun onItemClick(country: Country)
}
