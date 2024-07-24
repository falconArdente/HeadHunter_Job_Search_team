package ru.practicum.android.diploma.network.data.netapi

import ru.practicum.android.diploma.network.data.dto.HeadHunterRequest
import ru.practicum.android.diploma.network.data.dto.responses.Response

interface HeadHunterNetworkClient {
    suspend fun doRequest(request: HeadHunterRequest): Response
}
