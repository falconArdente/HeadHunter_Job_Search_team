package ru.practicum.android.diploma.network.dto.responses

import ru.practicum.android.diploma.network.dto.linked_classes.Locale

class LocalesResponse(
    val localeList: List<Locale>
) : Response()
