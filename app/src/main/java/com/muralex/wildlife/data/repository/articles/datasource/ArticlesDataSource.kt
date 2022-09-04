package com.muralex.wildlife.data.repository.articles.datasource

import com.muralex.wildlife.data.model.articles.ArticleData
import kotlinx.coroutines.flow.Flow

interface ArticlesDataSource {
    fun getSectionArticles(sectionId: String): Flow<List<ArticleData>>
    suspend fun getSectionArticlesList(sectionId: String): List<ArticleData>
    suspend fun getArticleById(articleId: String): ArticleData
}