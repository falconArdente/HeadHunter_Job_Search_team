package ru.practicum.android.diploma.search.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.JobListItemBinding
import ru.practicum.android.diploma.search.domain.model.Vacancy

class VacancyViewHolder(private val binding: JobListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vacancy: Vacancy) {
        binding.jobTitle.text = "${vacancy.name}, ${vacancy.area}"
        binding.jobEmployer.text = vacancy.employer?.name
        binding.jobSalary.text = if (vacancy.salary == null) {
            binding.root.resources.getString(R.string.no_salary_msg)
        } else if (vacancy.salary.to == null) {
            binding.root.resources.getString(
                R.string.salary_from,
                vacancy.salary.from.toString(),
                convertCurrencyToSymbol(vacancy)
            )
        } else {
            binding.root.resources.getString(
                R.string.salary_range,
                vacancy.salary.from.toString(),
                vacancy.salary.to.toString(),
                convertCurrencyToSymbol(vacancy)
            )
        }
        Glide.with(itemView)
            .load(vacancy.brandSnippet?.logo) // тут пока лого поставил, но не уверен, надо разбираться
            .placeholder(R.drawable.placeholder_logo)
            .centerCrop()
            .transform(RoundedCorners(itemView.resources.getDimensionPixelSize(R.dimen.dp12))) // вставить нашу функцию из util?
            .into(binding.jobImage)
    }
    private fun convertCurrencyToSymbol(vacancy: Vacancy): String {
        var currencyCode = vacancy.salary?.currency?:"RUR"
        if (currencyCode == "RUR") currencyCode = "RUB"
        val currency = java.util.Currency.getInstance(currencyCode)
        return currency.symbol
    }
}
