package ru.practicum.android.diploma.network.dto.responses

import ru.practicum.android.diploma.network.dto.linked.Country

class CountriesResponse(
    val countriesList: List<Country>
) : Response()
