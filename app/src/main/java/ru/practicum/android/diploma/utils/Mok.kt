package ru.practicum.android.diploma.utils

import ru.practicum.android.diploma.details.domain.model.AreaDetails
import ru.practicum.android.diploma.details.domain.model.ContactsDetails
import ru.practicum.android.diploma.details.domain.model.EmployerInfo
import ru.practicum.android.diploma.details.domain.model.LogoUrlsDetails
import ru.practicum.android.diploma.details.domain.model.VacancyDetails

class Mok {
    val vacancyDetails=object:VacancyDetails(
        id = "102145630",
        name = "Стропальщик",
        description =	"<p>Вахтовый метод работы в г. Ленск</p> <p> </p> <p><strong>Обязанности:</strong></p> <p>* Строповка тяжелых грузов;<br />* Строповка и увязка простых изделий, деталей, лесных и других аналогичных грузов массой свыше 25 тонн для их подъёма, перемещения и укладки;<br />* Строповка и увязка грузов средней сложности, лесных грузов (длиной свыше 3 до 6 метров);<br />* Строповка и увязка лесных грузов (длиною свыше 6 метров), деталей и узлов, требующих повышенной осторожности, технологического оборудования и связанных с ним конструкций;<br />* Подбор стропов и грузозахватных приспособлений в соответствии с массой и родом грузов;<br />* Определение массы груза по внешнему виду, технологические карты погрузо-разгрузочных работ.</p> <strong>Требования:</strong> <ul> <li>Удостоверение стропальщик 4 разряда</li> <li>Опыт работы от 1 года</li> </ul> <strong>Условия:</strong> <ul> <li>Оплата проезда до места работы и обратно</li> <li>Проживание за счет предприятия</li> <li>Вахтовая надбавка в размере 18 000</li> <li>Скидка в столовой на территории ООО &quot;Алмаздортранс&quot; в размере 25%.</li> </ul> <p> </p> <p> </p> <p> </p>" ,
        employer = EmployerInfo(
            employerName = "Алмаздортранс",
            contacts = null,
            area = AreaDetails(

            ),
            logo = LogoUrlsDetails(
                size90 = "https://img.hhcdn.ru/employer-logo/3932303.png",
                size240 = "https://img.hhcdn.ru/employer-logo/3932304.png",
                raw ="https://img.hhcdn.ru/employer-logo-original/872857.png"
            )
        ),
        jobDetails = ,
        vacancyUrl ="https://api.hh.ru/vacancies/101684044?locale=RU&host=hh.ru"
    )
}
