package ru.practicum.android.diploma.network.data.dto.responses

import ru.practicum.android.diploma.network.data.dto.linked.CountryDTO

class CountriesResponse(
    val countriesList: List<CountryDTO>
) : Response()
