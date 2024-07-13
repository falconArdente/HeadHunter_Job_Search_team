package ru.practicum.android.diploma.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentJobDetailsBinding
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.details.presentation.state.VacancyDetailsState
import ru.practicum.android.diploma.details.presentation.viewmodel.VacancyDetailsViewModel

class JobDetailsFragment : Fragment() {

    private var _binding: FragmentJobDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VacancyDetailsViewModel by viewModel<VacancyDetailsViewModel> {
        parametersOf(requireArguments().getString(VACANCY_ID_KEY))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJobDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchVacancyById()
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            render(it)
        }

        binding.shareButton.setOnClickListener {
            viewModel.shareVacancy()
        }

        binding.addToFavoritesButton.setOnClickListener {
            // вызвать метод viewModel по добавлению в избранное, когда будет готов
            // поменять также вид иконки
        }

        binding.email.setOnClickListener {
            viewModel.sendEmail(binding.email.text)
        }

        binding.phoneNumber.setOnClickListener {
            viewModel.dialNumber(binding.phoneNumber.text)
        }
    }

    private fun render(vacancyDetailsState: VacancyDetailsState) {
        if (vacancyDetailsState is VacancyDetailsState.Content) {
            val vacancyDetails: VacancyDetails = vacancyDetailsState.data
            binding.vacancyTitle.text = vacancyDetails.name
            binding.salary.text = if (vacancyDetails.salary == null) {
                binding.root.resources.getString(R.string.no_salary_msg)
            } else if (vacancyDetails.salary.to == null) {
                binding.root.resources.getString(
                    R.string.salary_from,
                    vacancyDetails.salary.from,
                    convertCurrencyToSymbol(vacancyDetails)
                )
            } else {
                binding.root.resources.getString(
                    R.string.salary_range,
                    vacancyDetails.salary.from,
                    vacancyDetails.salary.to,
                    convertCurrencyToSymbol(vacancyDetails)
                )
            }
            binding.responsibilities.text = vacancyDetails.description // размотать html в текст!!
            binding.requirements.text = vacancyDetails.description
            binding.workingConditions.text = vacancyDetails.description
            binding.keySkills.text = vacancyDetails.keySkills.forEach { "$it/" } // как список записать в textView?
            binding.email.text = vacancyDetails.contacts?.email
            binding.phoneNumber.text = vacancyDetails.contacts?.phones // Как из списка объектов вытянуть телефоны и положить их? Какой нужен? Formatted?

            // добавить проверки - если пустые поля, то isVisible = false сделать

            // Оформить Glide
//            Glide.with(itemView)
//                .load(vacancy.brandSnippet?.logo) // тут пока лого поставил, но не уверен, надо разбираться
//                .placeholder(R.drawable.placeholder_logo)
//                .centerCrop()
//                .transform(RoundedCorners(itemView.resources.getDimensionPixelSize(R.dimen.dp12)))
        } else {
            binding.errorImage.isVisible = true
        }
    }

    private fun convertCurrencyToSymbol(vacancy: VacancyDetails): String { // в Utils?
        var currencyCode = vacancy.salary?.currency
        if (currencyCode == "RUR") currencyCode = "RUB"
        val currency = java.util.Currency.getInstance(currencyCode)
        return currency.symbol
    }

    companion object {
        const val VACANCY_ID_KEY = "vacancy_id"
    }
}
