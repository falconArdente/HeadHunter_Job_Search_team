package ru.practicum.android.diploma.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.VacancyViewBinding
import ru.practicum.android.diploma.search.domain.model.Vacancy

class VacancyAdapter(
    vacancyList: List<Vacancy>,
    private val onVacancyClickListener: ((Vacancy) -> Unit),
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var vacancyList = vacancyList
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vacancyViewBinding = VacancyViewBinding.inflate(inflater, parent, false)
        return VacancyViewHolder(vacancyViewBinding)
    }

    override fun getItemCount(): Int = vacancyList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VacancyViewHolder) {
            holder.bind(vacancyList[position])
            holder.itemView.setOnClickListener {
                onVacancyClickListener.invoke(vacancyList[holder.adapterPosition])
            }
        }
    }

    fun updateList(updatedVacancyList: List<Vacancy>) {
        vacancyList = updatedVacancyList
        notifyDataSetChanged()
    }

}
