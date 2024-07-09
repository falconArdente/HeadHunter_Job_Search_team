package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity

@Entity
data class BrandSnippetEntity(
    val logo: String?,
    val logoXs: String?,
    val picture: String?,
    val pictureXs: String?,
    val vacancyId: Int

)
