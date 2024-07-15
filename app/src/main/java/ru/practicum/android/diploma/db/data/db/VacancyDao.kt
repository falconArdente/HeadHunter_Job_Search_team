package ru.practicum.android.diploma.db.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.db.data.db.entity.VacancyJoins

@Dao
interface VacancyDao {

    //NOT FUN INSERT

    @Query("Select 1 from vacancy where id = :vacancyId")
    suspend fun isExistsVacancy(vacancyId: Int): Int?

    @Query("Select * from vacancy where id = :vacancyId")
    suspend fun getVacancyById(vacancyId: Int): VacancyJoins?

    @Query("Select * from vacancy order by dateAdd")
    suspend fun getAllVacancy(): List<VacancyJoins>

    @Query("Select * from vacancy order by dateAdd LIMIT :vacancyByPage OFFSET :vacancyByPage * :pageNum ")
    suspend fun getAllVacancyByPage(pageNum: Int, vacancyByPage: Int = VACANCY_COUNT_ON_PAGE): List<VacancyJoins>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVacancy(vacancy: VacancyJoins)

    @Delete
    suspend fun deleteVacancy(vacancy: VacancyJoins)

    companion object{
        const val VACANCY_COUNT_ON_PAGE = 10
    }
}
