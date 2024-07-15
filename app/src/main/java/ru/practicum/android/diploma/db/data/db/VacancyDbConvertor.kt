package ru.practicum.android.diploma.db.data.db

import ru.practicum.android.diploma.db.data.db.entity.AreaEntity
import ru.practicum.android.diploma.db.data.db.entity.EmployerEntity
import ru.practicum.android.diploma.db.data.db.entity.EmployerJoinLogo
import ru.practicum.android.diploma.db.data.db.entity.LogosEntity
import ru.practicum.android.diploma.db.data.db.entity.SalaryEntity
import ru.practicum.android.diploma.db.data.db.entity.SalaryJoinCurrency
import ru.practicum.android.diploma.details.domain.model.AreaDetails
import ru.practicum.android.diploma.details.domain.model.EmployerDetails
import ru.practicum.android.diploma.details.domain.model.LogoUrlsDetails
import ru.practicum.android.diploma.details.domain.model.SalaryDetails

class VacancyDbConvertor(private val appDatabase:  AppDatabase
) {
    /*
    fun mapVacancy(vacancy: VacancyInfoEntity): VacancyDetails{
        return VacancyDetails(
            id = vacancy.vacancy.id.toString(),
            name = vacancy.vacancy.title,
            description = vacancy.vacancy.description.toString(),
            employer = if (vacancy.employer == null) null else mapEmployer(vacancy.employer),
            salary = if (vacancy.salary == null) null else mapSalary(vacancy.salary),
            area = if (vacancy.area == null) null else mapArea(vacancy.area),
            experience = if (vacancy.experience == null) null else null,
            contacts =  null
            employment =

            )
    }*/

    fun mapSalary(salary: SalaryDetails): SalaryEntity{
        return SalaryEntity(
            id  = 0,
            currencyCode = salary.currency,
            salaryFrom = salary.from,
            salaryTo = salary.to,
            gross = salary.gross)
    }

    fun mapSalary(salary: SalaryJoinCurrency): SalaryDetails{
        return SalaryDetails(
            currency = salary.currency.code,
            from = salary.salary.salaryFrom,
            to = salary.salary.salaryTo,
            gross = salary.salary.gross)


    }

    fun mapLogos(logo: LogoUrlsDetails, employerId: Int): LogosEntity{
        return LogosEntity(
            id = 0,
            size90 = logo.size90,
            size240 = logo.size240,
            employerId = employerId,
            raw = logo.raw
        )
    }

    fun mapLogos(logo: LogosEntity): LogoUrlsDetails{
        return LogoUrlsDetails(
            size90 = logo.size90,
            size240 = logo.size240,
            raw = logo.raw
            )
    }

    fun mapEmployer(employer: EmployerDetails, vacancyId: Int): EmployerEntity{
        return EmployerEntity(
            id = employer.id?.toInt() ?: 0,
            name = employer.name,
            isTrusted = employer.isTrusted,
            url = employer.url,
            vacancyUrl = employer.vacanciesUrl,
            vacancyId = vacancyId
        )
    }

    fun mapEmployer(employer: EmployerJoinLogo): EmployerDetails{
        return EmployerDetails(
            id = employer.employer.toString(),
            name = employer.employer.name,
            isTrusted = employer.employer.isTrusted,
            url = employer.employer.url,
            vacanciesUrl = employer.employer.vacancyUrl,
            logoUrls = mapLogos(employer.logoRow)
        )

    }

    fun  mapArea(area: AreaEntity): AreaDetails{
        return AreaDetails(
            id = area.id,
            name = area.name
        )
    }
    fun  mapArea(area: AreaDetails): AreaEntity{
        return AreaEntity(
            id = area.id,
            name = area.name,
            parentId = null,
            prepositional = null
        )
    }
}
