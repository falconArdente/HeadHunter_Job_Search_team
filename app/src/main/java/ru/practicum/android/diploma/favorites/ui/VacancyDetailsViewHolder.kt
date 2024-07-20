package ru.practicum.android.diploma.favorites.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.JobListItemBinding
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.utils.CurrencySymbol
import ru.practicum.android.diploma.utils.formatSalaryAmount
import ru.practicum.android.diploma.utils.toPx

class VacancyDetailsViewHolder(private val binding: JobListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val roundCornerValue = itemView.context.resources.getDimension(R.dimen.view_holder_round_corner)
    fun bind(vacancy: VacancyDetails, action: ClickerForVacancyDetail) {
        val allText = "${vacancy.name}, ${vacancy.employerInfo.area.name}"
        binding.root.setOnClickListener {
            action.onItemClick(vacancy)
        }
        binding.jobTitle.text = allText
        binding.jobEmployer.text = vacancy.employerInfo.employerName ?: ""
        binding.jobSalary.text = getSalaryText(vacancy)
        Glide.with(itemView)
            .load(vacancy.employerInfo.logo?.size240)
            .placeholder(R.drawable.placeholder_logo)
            .centerCrop()
            .transform(
                RoundedCorners(itemView.context.toPx(roundCornerValue.toInt()).toInt())
            )
            .into(binding.jobImage)
    }

    private fun getSalaryText(vacancy: VacancyDetails): String {
        val from: Int? = vacancy.jobInfo.salary?.from
        val to: Int? = vacancy.jobInfo.salary?.to
        return if (vacancy.jobInfo.salary == null) {
            binding.root.resources.getString(R.string.no_salary_msg)
        } else {
            if (from != null && to != null) {
                binding.root.resources.getString(
                    R.string.salary_range,
                    formatSalaryAmount(vacancy.jobInfo.salary.from),
                    formatSalaryAmount(vacancy.jobInfo.salary.to),
                    CurrencySymbol.getCurrencySymbol(vacancy.jobInfo.salary.currency)
                )
            } else if (from == null && to != null) {
                binding.root.resources.getString(
                    R.string.salary_to,
                    formatSalaryAmount(vacancy.jobInfo.salary.to),
                    CurrencySymbol.getCurrencySymbol(vacancy.jobInfo.salary.currency)
                )
            } else if (from != null && to == null) {
                binding.root.resources.getString(
                    R.string.salary_from,
                    formatSalaryAmount(vacancy.jobInfo.salary.from),
                    CurrencySymbol.getCurrencySymbol(vacancy.jobInfo.salary.currency)
                )
            } else {
                binding.root.resources.getString(R.string.no_salary_msg)
            }
        }
    }
}
