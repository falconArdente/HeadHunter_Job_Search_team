package ru.practicum.android.diploma.favorites.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.JobListItemBinding
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
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
        val from: Int? = vacancy.jobInfo.salary?.from
        val to: Int? = vacancy.jobInfo.salary?.to
        binding.jobSalary.text = if (vacancy.jobInfo.salary == null) {
            binding.root.resources.getString(R.string.no_salary_msg)
        } else {
            if (from != null && to != null) {
                binding.root.resources.getString(
                    R.string.salary_range,
                    vacancy.jobInfo.salary.from.toString(),
                    vacancy.jobInfo.salary.to.toString(),
                    getCurrencySymbol(vacancy)
                )
            } else if (from == null && to != null) {
                binding.root.resources.getString(
                    R.string.salary_to,
                    vacancy.jobInfo.salary.to.toString(),
                    getCurrencySymbol(vacancy)
                )
            } else if (from != null && to == null) {
                binding.root.resources.getString(
                    R.string.salary_from,
                    vacancy.jobInfo.salary.from.toString(),
                    getCurrencySymbol(vacancy)
                )
            } else {
                binding.root.resources.getString(R.string.no_salary_msg)
            }
        }
        Glide.with(itemView)
            .load(vacancy.employerInfo.logo?.size240)
            .placeholder(R.drawable.placeholder_logo)
            .centerCrop()
            .transform(
                RoundedCorners(itemView.context.toPx(roundCornerValue.toInt()).toInt())
            )
            .into(binding.jobImage)
    }

    private fun getCurrencySymbol(vacancy: VacancyDetails): String {
        if (vacancy.jobInfo.salary?.currency != null) {
            val codeForSymbol = if (vacancy.jobInfo.salary.currency == "RUR") {
                "RUB"
            } else {
                vacancy.jobInfo.salary.currency
            }
            return java.util.Currency.getInstance(codeForSymbol).symbol
        } else {
            return ""
        }
    }
}
