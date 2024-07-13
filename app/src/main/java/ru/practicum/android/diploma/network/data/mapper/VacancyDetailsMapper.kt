package ru.practicum.android.diploma.network.data.mapper

import ru.practicum.android.diploma.details.domain.model.AreaDetails
import ru.practicum.android.diploma.details.domain.model.ContactsDetails
import ru.practicum.android.diploma.details.domain.model.EmployerDetails
import ru.practicum.android.diploma.details.domain.model.EmploymentDetails
import ru.practicum.android.diploma.details.domain.model.ExperienceDetails
import ru.practicum.android.diploma.details.domain.model.LogoUrlsDetails
import ru.practicum.android.diploma.details.domain.model.PhoneDetails
import ru.practicum.android.diploma.details.domain.model.SalaryDetails
import ru.practicum.android.diploma.details.domain.model.SkillDetails
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.network.data.dto.linked.Area
import ru.practicum.android.diploma.network.data.dto.linked.Contacts
import ru.practicum.android.diploma.network.data.dto.linked.Employer
import ru.practicum.android.diploma.network.data.dto.linked.Employment
import ru.practicum.android.diploma.network.data.dto.linked.Experience
import ru.practicum.android.diploma.network.data.dto.linked.LogoUrls
import ru.practicum.android.diploma.network.data.dto.linked.Phone
import ru.practicum.android.diploma.network.data.dto.linked.Salary
import ru.practicum.android.diploma.network.data.dto.linked.Skill
import ru.practicum.android.diploma.network.data.dto.responses.VacancyByIdResponse

object VacancyDetailsMapper {
    fun VacancyByIdResponse.mapToDomain(): VacancyDetails {
        return VacancyDetails(
            id = this.id,
            name = this.name,
            description = this.description,
            employer = this.employer?.mapToDomain(),
            salary = this.salary?.mapToDomain(),
            contacts = this.contacts?.mapToDomain(),
            area = this.area.mapToDomain(),
            experience = this.experience?.mapToDomain(),
            employment = this.employment?.mapToDomain(),
            keySkills = this.keySkills.map { it.mapToDomain() },
            vacancyUrl = this.vacancyUrl,
        )
    }

    private fun Employer.mapToDomain(): EmployerDetails {
        return EmployerDetails(
            id = this.id,
            name = this.name,
            url = this.url,
            vacanciesUrl = this.vacanciesUrl,
            isTrusted = this.isTrusted,
            logoUrls = this.logoUrls?.mapToDomain(),
        )
    }

    private fun LogoUrls.mapToDomain(): LogoUrlsDetails {
        return LogoUrlsDetails(
            size90 = this.size90,
            size240 = this.size240,
            raw = this.raw
        )
    }

    private fun Salary.mapToDomain(): SalaryDetails {
        return SalaryDetails(
            currency = this.currency,
            from = this.from,
            gross = this.gross,
            to = this.to,
        )
    }

    private fun Contacts.mapToDomain(): ContactsDetails {
        return ContactsDetails(
            email = this.email,
            name = this.name,
            phones = this.phones?.map {
                it.mapToDomain()
            }

        )

    }

    private fun Phone.mapToDomain(): PhoneDetails {
        return PhoneDetails(
            city = this.city,
            comment = this.comment,
            country = this.country,
            formatted = this.formatted,
            number = this.number
        )
    }

    private fun Area.mapToDomain(): AreaDetails {
        return AreaDetails(
            id = this.id,
            name = this.name
        )
    }

    private fun Experience.mapToDomain(): ExperienceDetails {
        return ExperienceDetails(
            id = this.id,
            name = this.name
        )
    }

    private fun Employment.mapToDomain(): EmploymentDetails {
        return EmploymentDetails(
            id = this.id,
            name = this.name
        )
    }

    private fun Skill.mapToDomain(): SkillDetails {
        return SkillDetails(name = this.name)
    }

}
