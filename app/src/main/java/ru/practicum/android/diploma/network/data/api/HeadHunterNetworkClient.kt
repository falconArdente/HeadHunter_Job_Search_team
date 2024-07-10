package ru.practicum.android.diploma.network.data.api

import ru.practicum.android.diploma.network.data.dto.HeadHunterRequest
import ru.practicum.android.diploma.network.data.dto.responses.Response

interface HeadHunterNetworkClient {
    suspend fun doRequest(request: ru.practicum.android.diploma.network.data.dto.HeadHunterRequest): ru.practicum.android.diploma.network.data.dto.responses.Response
}
