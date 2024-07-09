package ru.practicum.android.diploma.network.dto.responses

import ru.practicum.android.diploma.network.dto.linkedClasses.Country

class CountriesResponse(
    val countriesList: List<Country>
) : Response()
