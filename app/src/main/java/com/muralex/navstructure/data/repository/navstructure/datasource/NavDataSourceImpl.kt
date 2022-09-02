package com.muralex.navstructure.data.repository.navstructure.datasource

import com.muralex.navstructure.data.mappers.json_data.SectionJsonToDataMapper
import com.muralex.navstructure.data.model.structure.SectionData
import timber.log.Timber

class NavDataSourceImpl(
    private val sectionsJsonUtil: SectionsJsonUtil,
    private val mapper: SectionJsonToDataMapper,
) : NavDataSource {

    private var sectionsList: List<SectionData> = emptyList()

    override suspend fun getSectionsList(parentId: String): List<SectionData> {
        val sections = getAllSections()
        return sections.filter { it.parent == parentId }
    }

    override suspend fun getSectionById(sectionId: String): SectionData {

        val sections = getAllSections()
        return sections.first { it.id == sectionId }
    }

    private suspend fun getAllSections(): List<SectionData> {
        return sectionsList.ifEmpty {
            val dataFromJson = sectionsJsonUtil.getSections()
            mapper.mapFromEntityList(dataFromJson)
        }
    }


}