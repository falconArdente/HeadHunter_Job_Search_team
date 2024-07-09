package ru.practicum.android.diploma.db.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.diploma.db.data.db.entity.AreaEntity
import ru.practicum.android.diploma.db.data.db.entity.BrandSnippetEntity
import ru.practicum.android.diploma.db.data.db.entity.ContactsEntity
import ru.practicum.android.diploma.db.data.db.entity.CountryEntity
import ru.practicum.android.diploma.db.data.db.entity.CurrencyEntity
import ru.practicum.android.diploma.db.data.db.entity.EmployerEntity
import ru.practicum.android.diploma.db.data.db.entity.ExperienceEntity
import ru.practicum.android.diploma.db.data.db.entity.IndustryEntity
import ru.practicum.android.diploma.db.data.db.entity.IndustrySphereEntity
import ru.practicum.android.diploma.db.data.db.entity.LogosEntity
import ru.practicum.android.diploma.db.data.db.entity.PhoneEntity
import ru.practicum.android.diploma.db.data.db.entity.SalaryEntity
import ru.practicum.android.diploma.db.data.db.entity.VacancyEntity
import ru.practicum.android.diploma.db.data.db.entity.VacancyPositionEntity

@Database(
    version = 1, entities = [
        AreaEntity::class,
        BrandSnippetEntity::class,
        ContactsEntity::class,
        CountryEntity::class,
        EmployerEntity::class,
        ExperienceEntity::class,
        IndustryEntity::class,
        IndustrySphereEntity::class,
        LogosEntity::class,
        PhoneEntity::class,
        SalaryEntity::class,
        VacancyEntity::class,
        VacancyPositionEntity::class
    ]
)

abstract class AppDatabase: RoomDatabase() {
}
