package ru.practicum.android.diploma.db.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
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

@Dao
abstract class VacancyDao {

    suspend fun deleteVacancyJoins(vacancyJoins: VacancyJoins) {
        deleteEmployerJoins(vacancyJoins.employerRow)

        deleteJobInfoJoins(vacancyJoins.jobInfoRow)

        deleteVacancy(vacancyJoins.vacancy)
    }

    private suspend fun deleteEmployerJoins(employerJoins: EmployerJoins) {
        deleteEmployer(employerJoins.employer)

        if (employerJoins.logoRow != null) {
            deleteLogo(employerJoins.logoRow)
        }

        deleteArea(employerJoins.areaRow)
    }

    private suspend fun deleteJobInfoJoins(jobInfoJoins: JobInfoJoins) {
        deleteJobInfo(jobInfoJoins.jobInfo)

        jobInfoJoins.skillList.forEach { skill ->
            deleteSkill(skill)
        }
        if (jobInfoJoins.salaryRow != null) {
            deleteSalary(jobInfoJoins.salaryRow)
        }
    }

    suspend fun insertVacancyJoins(vacancy: VacancyJoins) {
        val employerId = insertEmployerJoins(vacancy.employerRow)

        val jobInfoId = insertJobInfoJoins(vacancy.jobInfoRow)

        val vacancyEntity = VacancyEntity(
            id = 0,
            jobInfoId = jobInfoId,
            employerId = employerId,
            name = vacancy.vacancy.name,
            description = vacancy.vacancy.description,
            vacancyUrl = vacancy.vacancy.vacancyUrl,
            dateAdd = vacancy.vacancy.dateAdd
        )

        insertVacancy(vacancyEntity)
    }

    private suspend fun insertEmployerJoins(employerJoins: EmployerJoins): Long {
        val employerId = insertEmployer(employerJoins.employer)

        if (employerJoins.logoRow != null) {
            val logosEntity = LogosEntity(
                id = 0,
                size90 = employerJoins.logoRow.size90,
                size240 = employerJoins.logoRow.size240,
                employerId = employerId,
                raw = employerJoins.logoRow.raw
            )
            insertLogo(logosEntity)
        }

        val areaEntity = AreaEntity(
            id = employerJoins.areaRow.id,
            name = employerJoins.areaRow.name,
            employerId = employerId
        )
        insertArea(areaEntity)

        return employerId
    }

    private suspend fun insertJobInfoJoins(jobInfoJoins: JobInfoJoins): Long {
        val jobInfoId = insertJobInfo(jobInfoJoins.jobInfo)

        jobInfoJoins.skillList.forEach { skill ->
            val skillsEntity = SkillsEntity(
                id = 0,
                name = skill.name,
                jobInfoId = jobInfoId
            )
            insertSkill(skillsEntity)
        }
        if (jobInfoJoins.salaryRow != null) {
            val salaryEntity = SalaryEntity(
                id = 0,
                currency = jobInfoJoins.salaryRow.currency,
                salaryFrom = jobInfoJoins.salaryRow.salaryFrom,
                salaryTo = jobInfoJoins.salaryRow.salaryTo,
                gross = jobInfoJoins.salaryRow.gross,
                jobInfoId = jobInfoId
            )
            insertSalary(salaryEntity)
        }

        return jobInfoId
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertArea(areaEntity: AreaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertEmployer(employerEntity: EmployerEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertLogo(logosEntity: LogosEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSalary(salaryEntity: SalaryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSkill(skillsEntity: SkillsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertJobInfo(jobInfoEntity: JobInfoEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertVacancy(vacancyEntity: VacancyEntity)

    @Query("Select 1 from vacancy where id = :vacancyId")
    abstract suspend fun isExistsVacancy(vacancyId: Int): Int?

    @Transaction
    @Query("Select * from vacancy where id = :vacancyId")
    abstract suspend fun getVacancyById(vacancyId: Int): VacancyJoins?

    @Transaction
    @Query("Select * from vacancy order by dateAdd")
    abstract suspend fun getAllVacancy(): List<VacancyJoins>

    @Transaction
    @Query(
        "Select * from vacancy order by dateAdd " +
            "LIMIT :vacancyByPage OFFSET :vacancyByPage * :pageNum "
    )
    abstract suspend fun getAllVacancyByPage(
        pageNum: Int,
        vacancyByPage: Int = VACANCY_COUNT_ON_PAGE
    ): List<VacancyJoins>

    @Delete
    abstract suspend fun deleteJobInfo(jobInfo: JobInfoEntity)

    @Delete
    abstract suspend fun deleteEmployer(employer: EmployerEntity)

    @Delete
    abstract suspend fun deleteSalary(salary: SalaryEntity)

    @Delete
    abstract suspend fun deleteSkill(skill: SkillsEntity)

    @Delete
    abstract suspend fun deleteLogo(logo: LogosEntity)

    @Delete
    abstract suspend fun deleteArea(area: AreaEntity)

    @Delete
    abstract suspend fun deleteVacancy(vacancy: VacancyEntity)

    companion object {
        const val VACANCY_COUNT_ON_PAGE = 20
    }
}
