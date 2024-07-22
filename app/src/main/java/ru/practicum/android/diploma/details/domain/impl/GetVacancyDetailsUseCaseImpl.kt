package ru.practicum.android.diploma.details.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.details.domain.api.GetVacancyDetailsUseCase
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.utils.Resource

class GetVacancyDetailsUseCaseImpl(private val vacancyDetailsRepository: VacancyDetailsRepository) :
    GetVacancyDetailsUseCase {
    override suspend fun execute(id: String): Flow<Resource<VacancyDetails>> {
        return vacancyDetailsRepository.getVacancyById(id)
    }
}
