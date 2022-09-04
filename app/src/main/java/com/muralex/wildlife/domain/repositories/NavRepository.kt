package com.muralex.wildlife.domain.repositories

import com.muralex.wildlife.data.model.structure.SectionData
import com.muralex.wildlife.data.model.structure.SectionDataWithChildren

interface NavRepository {
    suspend fun getSectionsList(parentID: String): List<SectionData>
    suspend fun getSectionWithChildrenData(parentID: String): SectionDataWithChildren
}