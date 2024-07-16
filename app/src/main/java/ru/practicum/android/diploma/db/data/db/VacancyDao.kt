package ru.practicum.android.diploma.db.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    suspend fun insertVacancyJoins(vacancy: VacancyJoins){
        val employerId = insertEmployerJoins(vacancy.employerRow)

        val jobInfoId = insertJobInfoJoins(vacancy.jobInfoRow)

        vacancy.vacancy.employerId = employerId
        vacancy.vacancy.jobInfoId = jobInfoId

        insertVacancy(vacancy.vacancy)
    }

    private suspend fun insertEmployerJoins(employerJoins: EmployerJoins): Int {

        val employerId = insertEmployer(employerJoins.employer)

        if(employerJoins.logoRow!= null){
            employerJoins.logoRow.employerId = employerId
            insertLogo(employerJoins.logoRow)
        }

        employerJoins.areaRow.employerId = employerId
        insertArea(employerJoins.areaRow)

        return employerId
    }
    private suspend fun insertJobInfoJoins(jobInfoJoins: JobInfoJoins): Int{

        val jobInfoId = insertJobInfo(jobInfoJoins.jobInfo)

        jobInfoJoins.skillList.forEach { skill->
            skill.jobInfoId = jobInfoId
            insertSkill(skill)
        }
        if (jobInfoJoins.salaryRow != null){
            jobInfoJoins.salaryRow.jobInfoId = jobInfoId
            insertSalary(jobInfoJoins.salaryRow)
        }

        return jobInfoId
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertArea(areaEntity: AreaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertEmployer(employerEntity: EmployerEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertLogo(logosEntity: LogosEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSalary(salaryEntity: SalaryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSkill(skillsEntity: SkillsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertJobInfo(jobInfoEntity: JobInfoEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertVacancy(vacancyEntity: VacancyEntity)

    @Query("Select 1 from vacancy where id = :vacancyId")
    abstract suspend fun isExistsVacancy(vacancyId: Int): Int?

    @Query("Select * from vacancy where id = :vacancyId")
    abstract suspend fun getVacancyById(vacancyId: Int): VacancyJoins?

    @Query("Select * from vacancy order by dateAdd")
    abstract suspend fun getAllVacancy(): List<VacancyJoins>

    @Query("Select * from vacancy order by dateAdd LIMIT :vacancyByPage OFFSET :vacancyByPage * :pageNum ")
    abstract suspend fun getAllVacancyByPage(pageNum: Int, vacancyByPage: Int = VACANCY_COUNT_ON_PAGE): List<VacancyJoins>

    @Delete
    abstract suspend fun deleteVacancy(vacancy: VacancyJoins)

    companion object{
        const val VACANCY_COUNT_ON_PAGE = 20
    }
}
