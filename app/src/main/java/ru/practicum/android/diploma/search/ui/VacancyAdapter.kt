package ru.practicum.android.diploma.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.JobListItemBinding
import ru.practicum.android.diploma.databinding.ProgressBarRecyclerItemBinding
import ru.practicum.android.diploma.search.domain.model.Vacancy

private const val TYPE_VACANCY = 1
private const val TYPE_PROGRESS_BAR = 2

class VacancyAdapter(
    vacancyList: List<Vacancy>,
    private val onVacancyClickListener: SearchRecyclerViewEvent,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var vacancyList = vacancyList
        private set

    var isLastPage = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_VACANCY -> {
                val vacancyViewBinding = JobListItemBinding.inflate(inflater, parent, false)
                VacancyViewHolder(vacancyViewBinding)
            }

            TYPE_PROGRESS_BAR -> {
                val progressBarBinding = ProgressBarRecyclerItemBinding.inflate(inflater, parent, false)
                ProgressBarViewHolder(progressBarBinding)
            }

            else -> error("Unknown type: $viewType")
        }
    }

    override fun getItemCount(): Int = vacancyList.size

    override fun getItemViewType(position: Int): Int {
        return if (position == vacancyList.size && isLastPage) TYPE_PROGRESS_BAR else TYPE_VACANCY
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VacancyViewHolder) {
            holder.bind(vacancyList[position])
            holder.itemView.setOnClickListener {
                onVacancyClickListener.onItemClick(vacancyList[holder.adapterPosition])
            }
        } else if (holder is ProgressBarViewHolder) {
            holder.bind(isLastPage)
        }
    }

    fun updateIsLastPage(expression: Boolean) {
        isLastPage = expression
        if (expression) {
            vacancyList + Vacancy(
                id = String(),
                name = String(),
                salary = null,
                employer = null,
                brandSnippet = null,
                area = null
            )
            notifyItemChanged(vacancyList.size)
        } else {
            vacancyList.drop(vacancyList.size)
            notifyItemRemoved(vacancyList.size + 1)
            notifyItemRangeChanged(vacancyList.size, getItemCount())
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
