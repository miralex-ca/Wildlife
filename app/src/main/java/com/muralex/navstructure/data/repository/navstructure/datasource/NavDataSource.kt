package com.muralex.navstructure.data.repository.navstructure.datasource

import com.muralex.navstructure.data.model.structure.SectionData

interface NavDataSource {
    suspend fun getSectionsList(parentId: String): List<SectionData>
    suspend fun getSectionById(sectionId: String): SectionData
}