package ru.practicum.android.diploma.db.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.db.data.db.entity.CurrencyEntity
import ru.practicum.android.diploma.db.data.db.entity.EmployerEntity
import ru.practicum.android.diploma.db.data.db.entity.LogosEntity
import ru.practicum.android.diploma.db.data.db.entity.VacancyInfoEntity
import ru.practicum.android.diploma.db.data.db.entity.VacancyListEntity

@Dao
interface VacancyDao {

    @Query("Select 1 from vacancy where id = :vacancyId")
    suspend fun isExistsVacancy(vacancyId: Int): Int?

    @Query("Select * from vacancy where id = :vacancyId")
    suspend fun getVacancyById(vacancyId: Int): VacancyInfoEntity?

    @Query("Select * from vacancy order by dateAdd")
    suspend fun getAllVacancy(): List<VacancyListEntity>

    @Query("Select * from vacancy order by dateAdd LIMIT :vacancyByPage OFFSET :vacancyByPage * :pageNum ")
    suspend fun getAllVacancyByPage(pageNum: Int, vacancyByPage: Int = VACANCY_COUNT_ON_PAGE)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVacancy(vacancy: VacancyInfoEntity)

    @Delete
    suspend fun deleteVacancy(vacancy: VacancyInfoEntity)

    companion object{
        const val VACANCY_COUNT_ON_PAGE = 10
    }
}
