package com.muralex.wildlife.data.mappers.db_data

import com.muralex.wildlife.app.data.EntityMapper
import com.muralex.wildlife.app.utils.checkArticleImageUrl
import com.muralex.wildlife.data.model.articles.ArticleData
import com.muralex.wildlife.data.model.db.ArticleDataDB
import javax.inject.Inject

class ArticleDBToDataMapper @Inject constructor() : EntityMapper<ArticleDataDB, ArticleData> {

    override fun mapFromEntity(data: ArticleDataDB): ArticleData {
        return ArticleData(
            id = data.id,
            title = data.title,
            desc = data.desc,
            text = data.text,
            image = data.image.checkArticleImageUrl()
        )
    }

    fun mapFromEntityList(entitiesList: List<ArticleDataDB>): List<ArticleData> {
        return entitiesList.map { mapFromEntity(it) }
    }
}
