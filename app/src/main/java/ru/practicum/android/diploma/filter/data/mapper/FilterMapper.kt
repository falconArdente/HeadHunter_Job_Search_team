package ru.practicum.android.diploma.filter.data.mapper

import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaSuggestion
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.filter.domain.model.SphereOfIndustry
import ru.practicum.android.diploma.network.data.dto.linked.AreaDTO
import ru.practicum.android.diploma.network.data.dto.linked.AreaSuggestionDTO
import ru.practicum.android.diploma.network.data.dto.linked.CountryDTO
import ru.practicum.android.diploma.network.data.dto.linked.IndustryDTO
import ru.practicum.android.diploma.network.data.dto.linked.IndustrySphereDTO

object FilterMapper {
    fun toArea(areaDTO: AreaDTO): Area =
        Area(
            subAreas = areaDTO.subAreaDTOS.map { this.toArea(it) },
            id = areaDTO.id,
            name = areaDTO.name,
            parentId = areaDTO.parentId,
        )

    fun toCountry(countryDTO: CountryDTO): Country =
        Country(
            id = countryDTO.id,
            name = countryDTO.name,
            url = countryDTO.url,
        )

    fun toIndustry(industryDTO: IndustryDTO): Industry =
        Industry(
            id = industryDTO.id,
            industries = industryDTO.spheres.map { this.toSphereOfIndustry(industrySphereDTO = it) },
            name = industryDTO.name,
        )

    private fun toSphereOfIndustry(industrySphereDTO: IndustrySphereDTO): SphereOfIndustry =
        SphereOfIndustry(
            id = industrySphereDTO.id,
            name = industrySphereDTO.sphereName,
        )

    fun toAreaSuggestion(areaSuggestionDTO: AreaSuggestionDTO): AreaSuggestion =
        AreaSuggestion(
            id = areaSuggestionDTO.id,
            name = areaSuggestionDTO.name,
            url = areaSuggestionDTO.url,
        )
}
