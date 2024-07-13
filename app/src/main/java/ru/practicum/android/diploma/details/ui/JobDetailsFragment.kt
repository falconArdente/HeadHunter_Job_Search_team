package ru.practicum.android.diploma.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentJobDetailsBinding
import ru.practicum.android.diploma.details.domain.model.VacancyDetails

class JobDetailsFragment : Fragment() {

    private var _binding: FragmentJobDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentJobDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    companion object {

        private const val VACANCY_ID = "vacancy_id"
        private const val ROUNDED_CORNERS_SIZE = 12
        fun createArgs(vacancyID: String): Bundle = bundleOf(VACANCY_ID to vacancyID)
    }
}
