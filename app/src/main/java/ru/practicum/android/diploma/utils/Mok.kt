package ru.practicum.android.diploma.utils

import ru.practicum.android.diploma.details.domain.model.AreaDetails
import ru.practicum.android.diploma.details.domain.model.EmployerInfo
import ru.practicum.android.diploma.details.domain.model.JobInfo
import ru.practicum.android.diploma.details.domain.model.LogoUrlsDetails
import ru.practicum.android.diploma.details.domain.model.SalaryDetails
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.filter.domain.model.Area

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
    val areas = listOf(
        Area(
            id = "113",
            parentId = null,
            name = "Россия",
            subAreas = listOf(
                Area(
                    id = "1620",
                    parentId = "113",
                    name = "Республика Марий Эл",
                    subAreas = listOf(
                        Area(
                            id = "4228",
                            parentId = "1620",
                            name = "Виловатово",
                            subAreas = null
                        ),
                        Area(
                            id = "1621",
                            parentId = "1620",
                            name = "Волжск",
                            subAreas = null
                        ),
                        Area(
                            id = "1622",
                            parentId = "1620",
                            name = "Звенигово",
                            subAreas = null
                        )
                    )
                ),
                Area(
                    id = "1624",
                    parentId = "113",
                    name = "Республика Татарстан",
                    subAreas = listOf(
                        Area(
                            id = "1625",
                            parentId = "1624",
                            name = "Агрыз",
                            subAreas = null
                        ),
                        Area(
                            id = "1626",
                            parentId = "1624",
                            name = "Азнакаево",
                            subAreas = null
                        ),
                        Area(
                            id = "4167",
                            parentId = "1624",
                            name = "Айша",
                            subAreas = null
                        ),
                        Area(
                            id = "7193",
                            parentId = "1624",
                            name = "Аккузово",
                            subAreas = null
                        )
                    )
                )
            )
        ),
        Area(
            id = "1001",
            parentId = null,
            name = "Другие регионы",
            subAreas = listOf(
                Area(
                    id = "2112",
                    parentId = "1001",
                    name = "Абхазия",
                    subAreas = null
                ),
                Area(
                    id = "306",
                    parentId = "1001",
                    name = "Буркина Фасо",
                    subAreas = null
                ),
                Area(
                    id = "21",
                    parentId = "1001",
                    name = "Великобритания",
                    subAreas = null
                ),
                Area(
                    id = "114",
                    parentId = "1001",
                    name = "Венгрия",
                    subAreas = null
                ),
                Area(
                    id = "62",
                    parentId = "1001",
                    name = "Молдова",
                    subAreas = listOf(
                        Area(
                            id = "5049",
                            parentId = "62",
                            name = "Кишинёв",
                            subAreas = null
                        ),
                        Area(
                            id = "11285",
                            parentId = "62",
                            name = "Тирасполь",
                            subAreas = null
                        )
                    )
                ),
                Area(
                    id = "2440",
                    parentId = "1001",
                    name = "Ямайка",
                    subAreas = null
                ),
                Area(
                    id = "111",
                    parentId = "1001",
                    name = "Япония",
                    subAreas = null
                )
            )
        ),
        Area(
            id = "97",
            parentId = null,
            name = "Узбекистан",
            subAreas = listOf(
                Area(
                    id = "2763",
                    parentId = "97",
                    name = "Аккурган",
                    subAreas = null
                ),
                Area(
                    id = "2764",
                    parentId = "97",
                    name = "Акташ (Узбекистан)",
                    subAreas = null
                ),
                Area(
                    id = "2765",
                    parentId = "97",
                    name = "Алат (Узбекистан)",
                    subAreas = null
                )
            )
        )
    )
}
