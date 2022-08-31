package com.muralex.navstructure.domain.usecases.articles

import com.muralex.navstructure.domain.data.article.DetailArticleUI
import com.muralex.navstructure.domain.mappers.ArticleDataToArticleMapper
import com.muralex.navstructure.domain.repositories.ArticlesRepository
import javax.inject.Inject

class GetDetailArticleUseCase @Inject constructor(
    private val repository: ArticlesRepository,
    private val mapper: ArticleDataToArticleMapper,
) {
    suspend operator fun invoke(articleId: String, sectionID: String): DetailArticleUI {

        val neighbors = repository.getArticleNeighbors(articleId, sectionID)

        return DetailArticleUI(
            article = mapper.mapFromEntity(repository.getArticleById(articleId)),
            sectionId = sectionID,
            previousId = neighbors.getString("prev"),
            nextId = neighbors.getString("next")
        )
    }
}