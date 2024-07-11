package ru.practicum.android.diploma.network.data.dto.responses

import ru.practicum.android.diploma.network.data.dto.linked.Country

class CountriesResponse(
    val countriesList: List<Country>
) : Response()
