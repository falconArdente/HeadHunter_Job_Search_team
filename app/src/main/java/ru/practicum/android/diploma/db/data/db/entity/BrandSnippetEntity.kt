package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BrandSnippet")
data class BrandSnippetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val logo: String?,
    val logoXs: String?,
    val picture: String?,
    val pictureXs: String?,
    val vacancyId: Int

)
