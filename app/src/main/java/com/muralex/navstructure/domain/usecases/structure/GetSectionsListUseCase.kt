package com.muralex.navstructure.domain.usecases.structure

import com.muralex.navstructure.app.utils.Constants.SECTION_ROOT_ID
import com.muralex.navstructure.domain.data.navstructure.Section
import com.muralex.navstructure.domain.mappers.SectionDataToSectionMapper
import com.muralex.navstructure.domain.repositories.NavRepository
import javax.inject.Inject

class GetSectionsListUseCase @Inject constructor(
    private val repository: NavRepository,
    private val mapper: SectionDataToSectionMapper,
) {
    suspend operator fun invoke(): List<Section> {
        return mapper.mapFromEntityList(repository.getSectionsList(SECTION_ROOT_ID))
    }
}