package com.muralex.wildlife.data.repository.navstructure.datasource

import com.muralex.wildlife.data.model.structure.SectionData

interface NavDataSource {
    suspend fun getSectionsList(parentId: String): List<SectionData>
    suspend fun getSectionById(sectionId: String): SectionData
}