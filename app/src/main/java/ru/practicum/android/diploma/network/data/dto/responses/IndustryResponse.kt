package ru.practicum.android.diploma.network.data.dto.responses

import ru.practicum.android.diploma.network.data.dto.linked.IndustryDTO

class IndustryResponse(
    val industriesList: List<IndustryDTO>
) : Response()
