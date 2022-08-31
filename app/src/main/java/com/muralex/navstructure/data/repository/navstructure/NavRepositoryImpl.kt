package com.muralex.navstructure.data.repository.navstructure

import com.muralex.navstructure.data.model.structure.SectionData
import com.muralex.navstructure.data.model.structure.SectionDataWithChildren
import com.muralex.navstructure.data.repository.navstructure.datasource.NavDataSource
import com.muralex.navstructure.domain.repositories.NavRepository

class NavRepositoryImpl(
    private val navDataSource: NavDataSource,
) : NavRepository {

    override suspend fun getSectionsList(parentID: String): List<SectionData> {
        return navDataSource.getSectionsList(parentID)
    }

    override suspend fun getSectionWithChildrenData(parentID: String): SectionDataWithChildren {
        return getSectionWithChildren(parentID)
    }

    private suspend fun getSectionWithChildren(sectionId: String): SectionDataWithChildren {
        val section = navDataSource.getSectionById(sectionId)
        val subsections = navDataSource.getSectionsList(sectionId)
        return SectionDataWithChildren(section, subsections)
    }

}