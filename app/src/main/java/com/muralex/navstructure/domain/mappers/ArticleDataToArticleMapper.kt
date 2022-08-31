package com.muralex.navstructure.domain.mappers

import com.muralex.navstructure.app.data.EntityMapper
import com.muralex.navstructure.app.utils.Constants.ICONS_FOLDER
import com.muralex.navstructure.data.model.articles.ArticleData
import com.muralex.navstructure.data.model.structure.SectionData
import com.muralex.navstructure.data.model.structure.SectionJsonData
import com.muralex.navstructure.domain.data.article.Article
import com.muralex.navstructure.domain.data.navstructure.Section
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleDataToArticleMapper @Inject constructor() : EntityMapper<ArticleData, Article> {

    override fun mapFromEntity(data: ArticleData): Article {
        return Article(
            id = data.id,
            title = data.title,
            desc = data.desc,
            text = data.text,
            image = data.image,
        )
    }

    fun mapFromEntityList(entitiesList: List<ArticleData>): List<Article> {
        return entitiesList.map { mapFromEntity(it) }
    }

}
