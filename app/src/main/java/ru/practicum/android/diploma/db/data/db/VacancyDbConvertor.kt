package ru.practicum.android.diploma.db.data.db

import ru.practicum.android.diploma.db.data.db.entity.AreaEntity
import ru.practicum.android.diploma.db.data.db.entity.EmployerEntity
import ru.practicum.android.diploma.db.data.db.entity.EmployerJoins
import ru.practicum.android.diploma.db.data.db.entity.JobInfoEntity
import ru.practicum.android.diploma.db.data.db.entity.JobInfoJoins
import ru.practicum.android.diploma.db.data.db.entity.LogosEntity
import ru.practicum.android.diploma.db.data.db.entity.SalaryEntity
import ru.practicum.android.diploma.db.data.db.entity.SkillsEntity
import ru.practicum.android.diploma.db.data.db.entity.VacancyEntity
import ru.practicum.android.diploma.db.data.db.entity.VacancyJoins
import ru.practicum.android.diploma.details.domain.model.AreaDetails
import ru.practicum.android.diploma.details.domain.model.EmployerInfo
import ru.practicum.android.diploma.details.domain.model.JobInfo
import ru.practicum.android.diploma.details.domain.model.LogoUrlsDetails
import ru.practicum.android.diploma.details.domain.model.SalaryDetails
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import java.text.SimpleDateFormat
import java.util.Date

class VacancyDbConvertor {

    fun mapVacancy(vacancy: VacancyJoins): VacancyDetails {
        return VacancyDetails(
            id = vacancy.vacancy.id.toString(),
            name = vacancy.vacancy.name,
            description = vacancy.vacancy.description,
            employer = mapEmployer(vacancy.employerRow),
            jobDetails = mapJobDetails(vacancy.jobInfoRow),
            vacancyUrl = vacancy.vacancy.vacancyUrl
        )
    }

    fun mapVacancy(vacancy: VacancyDetails): VacancyJoins {
        return VacancyJoins(
            vacancy = mapVacancyEntity(vacancy),
            jobInfoRow = mapJobDetails(vacancy.jobDetails),
            employerRow = mapEmployerJoin(vacancy.employer)
        )
    }

    private fun mapEmployerJoin(employer: EmployerInfo): EmployerJoins {
        return EmployerJoins(
            employer = mapEmployer(employer),
            areaRow = mapArea(employer.area),
            logoRow = if (employer.logo != null) mapLogo(employer.logo) else null
        )
    }

    private fun mapVacancyEntity(vacancy: VacancyDetails): VacancyEntity {
        return VacancyEntity(
            id = vacancy.id.toLong(),
            jobInfoId = 0,
            employerId = 0,
            name = vacancy.name,
            description = vacancy.description,
            vacancyUrl = vacancy.vacancyUrl,
            dateAdd = SimpleDateFormat("yyyy/dd/M hh:mm:ss").format(Date())
        )
    }

    private fun mapJobDetails(jobDetails: JobInfoJoins): JobInfo {
        return JobInfo(
            salary = if (jobDetails.salaryRow != null) mapSalary(jobDetails.salaryRow) else null,
            experience = jobDetails.jobInfo.experience,
            employment = jobDetails.jobInfo.employment,
            keySkills = jobDetails.skillList.map { skill -> mapSkills(skill) }
        )
    }

    private fun mapJobDetails(jobDetails: JobInfo): JobInfoJoins {
        return JobInfoJoins(
            jobInfo = mapJobInfo(jobDetails),
            salaryRow = if (jobDetails.salary != null) mapSalary(jobDetails.salary) else null,
            skillList = jobDetails.keySkills.map { skill -> mapSkills(skill) }
        )
    }

    private fun mapSkills(skill: SkillsEntity): String {
        return skill.name
    }

    private fun mapSkills(skill: String): SkillsEntity {
        return SkillsEntity(
            id = 0,
            jobInfoId = 0,
            name = skill
        )
    }

    private fun mapJobInfo(jobInfo: JobInfo): JobInfoEntity {
        return JobInfoEntity(
            id = 0,
            experience = jobInfo.experience,
            employment = jobInfo.employment
        )
    }

    private fun mapEmployer(employer: EmployerInfo): EmployerEntity {
        return EmployerEntity(
            id = 0,
            name = employer.employerName
        )
    }

    private fun mapEmployer(employer: EmployerJoins): EmployerInfo {
        return EmployerInfo(
            employerName = employer.employer.name,
            contacts = null,
            area = mapArea(employer.areaRow),
            logo = if (employer.logoRow != null) mapLogo(employer.logoRow) else null
        )
    }

    private fun mapSalary(salary: SalaryDetails): SalaryEntity {
        return SalaryEntity(
            id = 0,
            currency = salary.currency,
            salaryFrom = salary.from,
            salaryTo = salary.to,
            gross = salary.gross,
            jobInfoId = 0
        )
    }

    private fun mapSalary(salary: SalaryEntity): SalaryDetails {
        return SalaryDetails(
            currency = salary.currency,
            from = salary.salaryFrom,
            to = salary.salaryTo,
            gross = salary.gross
        )
    }

    private fun mapLogo(logo: LogoUrlsDetails): LogosEntity {
        return LogosEntity(
            id = 0,
            size90 = logo.size90,
            size240 = logo.size240,
            employerId = 0,
            raw = logo.raw
        )
    }

    private fun mapLogo(logo: LogosEntity): LogoUrlsDetails {
        return LogoUrlsDetails(
            size90 = logo.size90,
            size240 = logo.size240,
            raw = logo.raw
        )
    }

    private fun mapArea(area: AreaEntity): AreaDetails {
        return AreaDetails(
            id = area.id,
            name = area.name
        )
    }

    private fun mapArea(area: AreaDetails): AreaEntity {
        return AreaEntity(
            id = area.id,
            name = area.name,
            employerId = 0
        )
    }
}
