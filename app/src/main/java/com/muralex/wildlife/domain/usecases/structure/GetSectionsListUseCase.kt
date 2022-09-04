package com.muralex.wildlife.domain.usecases.structure

import com.muralex.wildlife.app.utils.Constants.SECTION_ROOT_ID
import com.muralex.wildlife.app.utils.Dispatchers
import com.muralex.wildlife.domain.data.navstructure.Section
import com.muralex.wildlife.domain.mappers.SectionDataToSectionMapper
import com.muralex.wildlife.domain.repositories.NavRepository
import javax.inject.Inject

class GetSectionsListUseCase @Inject constructor(
    private val repository: NavRepository,
    private val mapper: SectionDataToSectionMapper,
    private val dispatcher: Dispatchers,
) {
    suspend operator fun invoke(): List<Section> = dispatcher.background {
         mapper.mapFromEntityList(repository.getSectionsList(SECTION_ROOT_ID))
    }
}