package ru.practicum.android.diploma.network.api

import ru.practicum.android.diploma.network.dto.HeadHunterRequest
import ru.practicum.android.diploma.network.dto.responses.Response

interface HeadHunterNetworkClient {
    suspend fun doRequest(request: HeadHunterRequest): Response
}
