package ru.practicum.android.diploma.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.JobListItemBinding
import ru.practicum.android.diploma.search.domain.model.Vacancy

class VacancyAdapter(
    vacancyList: List<Vacancy>,
    private val onVacancyClickListener: SearchRecyclerViewEvent
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var vacancyList = vacancyList
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vacancyViewBinding = JobListItemBinding.inflate(inflater, parent, false)
        return VacancyViewHolder(vacancyViewBinding, onVacancyClickListener)
    }

    override fun getItemCount(): Int = vacancyList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VacancyViewHolder) {
            holder.bind(vacancyList[position])
        }
    }

    fun updateList(updatedVacancyList: List<Vacancy>) {
        vacancyList = updatedVacancyList
        notifyDataSetChanged()
    }
}

interface SearchRecyclerViewEvent {
    fun onItemClick(vacancy: Vacancy)
}
