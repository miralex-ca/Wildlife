package com.muralex.navstructure.domain.usecases.structure

import com.muralex.navstructure.domain.data.navstructure.SectionWithChildren
import com.muralex.navstructure.domain.mappers.SectionDataToSectionMapper
import com.muralex.navstructure.domain.repositories.NavRepository
import javax.inject.Inject

class GetSectionWithChildrenUseCase @Inject constructor(
    private val repository: NavRepository,
    private val mapper: SectionDataToSectionMapper,
) {
    suspend operator fun invoke(sectionID: String): SectionWithChildren {
        val sectionData = repository.getSectionWithChildrenData(sectionID)
        val section = mapper.mapFromEntity(sectionData.section)
        val children = mapper.mapFromEntityList(sectionData.subSections)
        return SectionWithChildren(section, children)
    }
}