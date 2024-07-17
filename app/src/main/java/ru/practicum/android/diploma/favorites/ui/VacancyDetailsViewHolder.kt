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
        val allText = "${vacancy.name}, ${vacancy.employer.area.name}"

        binding.root.setOnClickListener {
            action.onItemClick(vacancy)
        }
        binding.jobTitle.text = allText
        binding.jobEmployer.text = vacancy.employer.employerName ?: ""
        binding.jobSalary.text = if (vacancy.jobDetails.salary == null) {
            binding.root.resources.getString(R.string.no_salary_msg)
        } else if (vacancy.jobDetails.salary.to == null) {
            binding.root.resources.getString(
                R.string.salary_from,
                vacancy.jobDetails.salary.from.toString(),
                convertCurrencyToSymbol(vacancy.jobDetails.salary.currency ?: "RUR")
            )
        } else {
            binding.root.resources.getString(
                R.string.salary_range,
                vacancy.jobDetails.salary.from.toString(),
                vacancy.jobDetails.salary,
                if (vacancy.jobDetails.salary.currency != null) {
                    convertCurrencyToSymbol(vacancy.jobDetails.salary.currency)
                } else {
                    ""
                }
            )
        }
        Glide.with(itemView)
            .load(vacancy.employer.logo)
            .placeholder(R.drawable.placeholder_logo)
            .centerCrop()
            .transform(
                RoundedCorners(itemView.context.toPx(roundCornerValue.toInt()).toInt())
            )
            .into(binding.jobImage)
    }

    private fun convertCurrencyToSymbol(currencyCode: String): String {
        val codeForSymbol = if (currencyCode == "RUR") "RUB" else currencyCode
        val currency = java.util.Currency.getInstance(codeForSymbol)
        return currency.symbol
    }
}
