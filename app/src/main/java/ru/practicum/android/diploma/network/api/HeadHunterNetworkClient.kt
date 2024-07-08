package ru.practicum.android.diploma.network.api

import ru.practicum.android.diploma.network.dto.responses.Response
import ru.practicum.android.diploma.network.dto.HeadHunterRequest

interface HeadHunterNetworkClient {
    suspend fun doRequest(request: HeadHunterRequest): Response
}
