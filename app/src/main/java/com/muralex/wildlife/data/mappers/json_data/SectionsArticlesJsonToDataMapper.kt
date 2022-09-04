package com.muralex.wildlife.data.mappers.json_data

import com.muralex.wildlife.app.data.EntityMapper
import com.muralex.wildlife.data.model.articles.SectionArticlesData
import com.muralex.wildlife.data.model.json.SectionArticlesJsonData
import javax.inject.Inject

class SectionsArticlesJsonToDataMapper @Inject constructor() :
    EntityMapper<SectionArticlesJsonData, SectionArticlesData> {

    override fun mapFromEntity(data: SectionArticlesJsonData): SectionArticlesData {
        return SectionArticlesData(
            sectionId = data.sectionId ?: "",
            articlesIdsList = data.articles?.map { it[0] } ?: emptyList()
        )
    }

    fun mapFromEntityList(entitiesList: List<SectionArticlesJsonData>): List<SectionArticlesData> {
        return entitiesList.map { mapFromEntity(it) }
    }

}
