package ru.practicum.android.diploma.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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

        // Для тестирования верстки, сюда нужно будет передать данные о текущей вакансии после получения их по id
        val currentVacancyDetails = VacancyDetails(
            title = "Android-разработчик",
            salary = "от 100 000₽",
            company = "Еда",
            city = "Москва",
            imageUrl = null,
            experience = "От 1 года до 3 лет",
            occupation = "Полная занятость, Удаленная работа",
            responsibilities = "•  Разрабатывать новую функциональность приложения\n" +
                "•  Помогать с интеграцией нашего SDK в другие приложения\n" +
                "•  Проектировать большие новые модули\n" +
                "•  Писать UI- и unit-тесты\n" +
                "•  Следить за работоспособностью сервиса и устранять технический долг",
            requirements = "•  100% Kotlin\n•  WebRTC\n•  CI по модели Trunk Based Development",
            conditions = "•  сильная команда, с которой можно расти\n•  возможность влиять на процесс и результат\n" +
                "•  расширенная программа ДМС: стоматология, обследования, вызов врача на дом и многое другое",
            keySkills = "•  Знание классических алгоритмов и структур данных\n" +
                "•  Программирование для Android больше одного года\n•  Знание WebRTC",
        )

        initializeJobDetailsFragment(currentVacancyDetails)

        // Получение id вакансии для формирования запроса в сеть
        val currentVacancyLink = requireArguments().getString(VACANCY_ID)
    }

    private fun initializeJobDetailsFragment(vacancy: VacancyDetails) {
        binding.jobTitle.text = vacancy.title
        binding.jobSalary.text = vacancy.salary
        binding.jobCompany.text = vacancy.company
        binding.jobCity.text = vacancy.city

        Glide.with(binding.jobImage).load(vacancy.imageUrl).transform(RoundedCorners(ROUNDED_CORNERS_SIZE))
            .placeholder(R.drawable.placeholder_logo).into(binding.jobImage)

        binding.jobExperienceText1.text = vacancy.experience
        binding.jobExperienceText2.text = vacancy.occupation

        if (vacancy.responsibilities == null) {
            binding.jobResponsibilitiesTitle.visibility = View.GONE
        } else {
            binding.jobResponsibilitiesText.text = vacancy.responsibilities
        }

        if (vacancy.requirements == null) {
            binding.jobRequirementsTitle.visibility = View.GONE
        } else {
            binding.jobRequirementsText.text = vacancy.requirements
        }

        if (vacancy.conditions == null) {
            binding.jobConditionsTitle.visibility = View.GONE
        } else {
            binding.jobConditionsText.text = vacancy.conditions
        }

        if (vacancy.keySkills == null) {
            binding.jobKeySkillsTitle.visibility = View.GONE
        } else {
            binding.jobKeySkillsText.text = vacancy.keySkills
        }
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

        private const val VACANCY_ID = "vacancy_id"
        private const val ROUNDED_CORNERS_SIZE = 12
        fun createArgs(vacancyID: String): Bundle = bundleOf(VACANCY_ID to vacancyID)
    }
}
