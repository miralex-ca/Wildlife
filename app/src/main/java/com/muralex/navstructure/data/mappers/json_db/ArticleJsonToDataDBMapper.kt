package com.muralex.navstructure.data.mappers.json_db

import com.muralex.navstructure.app.data.EntityMapper
import com.muralex.navstructure.data.model.db.ArticleDataDB
import com.muralex.navstructure.data.model.json.ArticleJsonData
import javax.inject.Inject

class ArticleJsonToDataDBMapper @Inject constructor() :
    EntityMapper<ArticleJsonData, ArticleDataDB> {

    override fun mapFromEntity(data: ArticleJsonData): ArticleDataDB {
        return ArticleDataDB(
            id = data.id ?: "",
            title = data.title ?: "",
            desc = data.desc ?: "",
            text = data.text ?: "",
            image = data.image ?: ""
        )
    }

    fun mapFromEntityList(entitiesList: List<ArticleJsonData>): List<ArticleDataDB> {
        return entitiesList.map { mapFromEntity(it) }
    }

}
