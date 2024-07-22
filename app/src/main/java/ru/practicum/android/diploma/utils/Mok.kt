package ru.practicum.android.diploma.utils

import ru.practicum.android.diploma.details.domain.model.AreaDetails
import ru.practicum.android.diploma.details.domain.model.EmployerInfo
import ru.practicum.android.diploma.details.domain.model.JobInfo
import ru.practicum.android.diploma.details.domain.model.LogoUrlsDetails
import ru.practicum.android.diploma.details.domain.model.SalaryDetails
import ru.practicum.android.diploma.details.domain.model.VacancyDetails

object Mok {
    val vacancyDetailsMok = VacancyDetails(
        id = "102145630",
        name = "Стропальщик",
        description = "<p>Вахтовый метод работы в г. Ленск</p> <p> </p> <p><strong>Обязанности:" +
            "</strong></p> <p>* Строповка тяжелых грузов;<br />* Строповка и увязка простых изделий, деталей, ",
        employerInfo = EmployerInfo(
            employerName = "Алмаздортранс",
            contacts = null,
            area = AreaDetails(
                id = "68",
                name = "Омск"
            ),
            logo = LogoUrlsDetails(
                size90 = "https://img.hhcdn.ru/employer-logo/3932303.png",
                size240 = "https://img.hhcdn.ru/employer-logo/3932304.png",
                raw = "https://img.hhcdn.ru/employer-logo-original/872857.png"
            )
        ),
        jobInfo = JobInfo(
            salary = SalaryDetails(
                currency = "RUR",
                from = 100_000,
                gross = true,
                to = null,
            ),
            experience = "between1And3",
            employment = "Полная занятость",
            keySkills = listOf("skill", "SKILLL")
        ),
        vacancyUrl = "https://api.hh.ru/vacancies/101684044?locale=RU&host=hh.ru"
    )
}
