package com.muralex.wildlife.data.mappers.json_db

import com.muralex.wildlife.app.data.EntityMapper
import com.muralex.wildlife.data.model.db.ArticleSectionDataDB
import com.muralex.wildlife.data.model.json.SectionArticlesJsonData
import javax.inject.Inject

class SectionsArticlesJsonToDBMapper @Inject constructor() :
    EntityMapper<SectionArticlesJsonData, List<ArticleSectionDataDB>> {

    override fun mapFromEntity(data: SectionArticlesJsonData): List<ArticleSectionDataDB> {
        return data.articles?.map {
            ArticleSectionDataDB(
                sectionId = data.sectionId ?: "",
                articleId = it[0]
            )
        } ?: emptyList()
    }

    fun mapFromEntityList(entitiesList: List<SectionArticlesJsonData>): List<ArticleSectionDataDB> {
        return entitiesList.flatMap { mapFromEntity(it) }
    }

}
