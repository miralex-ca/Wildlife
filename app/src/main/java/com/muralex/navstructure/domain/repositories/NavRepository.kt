package com.muralex.navstructure.domain.repositories

import com.muralex.navstructure.data.model.structure.SectionData
import com.muralex.navstructure.data.model.structure.SectionDataWithChildren
import com.muralex.navstructure.domain.data.navstructure.SectionWithChildren

interface NavRepository {
    suspend fun getSectionsList(parentID: String): List<SectionData>
    suspend fun getSectionWithChildrenData(parentID: String): SectionDataWithChildren
}