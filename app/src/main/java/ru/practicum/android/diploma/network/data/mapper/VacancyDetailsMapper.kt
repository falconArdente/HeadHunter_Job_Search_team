package ru.practicum.android.diploma.network.data.mapper

import ru.practicum.android.diploma.details.domain.model.AreaDetails
import ru.practicum.android.diploma.details.domain.model.ContactsDetails
import ru.practicum.android.diploma.details.domain.model.EmployerInfo
import ru.practicum.android.diploma.details.domain.model.JobInfo
import ru.practicum.android.diploma.details.domain.model.LogoUrlsDetails
import ru.practicum.android.diploma.details.domain.model.SalaryDetails
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.network.data.dto.responses.VacancyByIdResponse

object VacancyDetailsMapper {
    fun VacancyByIdResponse.mapToDomain(): VacancyDetails {
        return VacancyDetails(
            id = this.id,
            name = this.name,
            description = this.description,
            employer = mapToEmployerInfo(this),
            jobDetails = mapToJobInfo(this),
            vacancyUrl = this.vacancyUrl,
        )
    }

    private fun mapToEmployerInfo(vacancyByIdResponse: VacancyByIdResponse): EmployerInfo {
        return EmployerInfo(
            employerName = vacancyByIdResponse.employer?.name,
            contacts = ContactsDetails(
                email = vacancyByIdResponse.contacts?.email,
                phones = vacancyByIdResponse.contacts?.phones?.map { it.formatted },
                name = vacancyByIdResponse.contacts?.name
            ),
            area = AreaDetails(name = vacancyByIdResponse.area.name, id = vacancyByIdResponse.area.id),
            logo = LogoUrlsDetails(
                size90 = vacancyByIdResponse.employer?.logoUrls?.size90,
                size240 = vacancyByIdResponse.employer?.logoUrls?.size240,
                raw = vacancyByIdResponse.employer?.logoUrls?.raw
            ),
        )
    }

    private fun mapToJobInfo(vacancyByIdResponse: VacancyByIdResponse): JobInfo {
        return JobInfo(
            salary = SalaryDetails(
                currency = vacancyByIdResponse.salary?.currency,
                from = vacancyByIdResponse.salary?.from,
                to = vacancyByIdResponse.salary?.to,
                gross = vacancyByIdResponse.salary?.gross,
            ),
            experience = vacancyByIdResponse.experience?.name,
            employment = vacancyByIdResponse.employment?.name,
            keySkills = vacancyByIdResponse.keySkills.map { it.name },
        )
    }
}
