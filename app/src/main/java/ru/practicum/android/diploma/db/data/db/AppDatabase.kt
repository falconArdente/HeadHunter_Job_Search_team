package ru.practicum.android.diploma.db.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.diploma.db.data.db.entity.AreaEntity
import ru.practicum.android.diploma.db.data.db.entity.EmployerEntity
import ru.practicum.android.diploma.db.data.db.entity.JobInfoEntity
import ru.practicum.android.diploma.db.data.db.entity.LogosEntity
import ru.practicum.android.diploma.db.data.db.entity.SalaryEntity
import ru.practicum.android.diploma.db.data.db.entity.SkillsEntity
import ru.practicum.android.diploma.db.data.db.entity.VacancyEntity

@Database(
    version = 2,
    entities = [
        AreaEntity::class,
        EmployerEntity::class,
        JobInfoEntity::class,
        LogosEntity::class,
        SalaryEntity::class,
        SkillsEntity::class,
        VacancyEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyDao(): VacancyDao
}
