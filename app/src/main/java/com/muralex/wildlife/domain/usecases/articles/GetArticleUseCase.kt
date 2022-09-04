package com.muralex.wildlife.domain.usecases.articles

import com.muralex.wildlife.domain.data.article.Article
import com.muralex.wildlife.domain.mappers.ArticleDataToArticleMapper
import com.muralex.wildlife.domain.repositories.ArticlesRepository
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val repository: ArticlesRepository,
    private val mapper: ArticleDataToArticleMapper,
) {
    suspend operator fun invoke(articleId: String): Article {
        return mapper.mapFromEntity(repository.getArticleById(articleId))
    }
}