package com.muralex.wildlife.domain.repositories

import android.os.Bundle
import com.muralex.wildlife.data.model.articles.ArticleData
import com.muralex.wildlife.data.model.structure.SectionDataWithArticles
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    suspend fun getSectionDataWithArticles(parentID: String): Flow<SectionDataWithArticles>
    fun getArticlesList(sectionID: String): Flow<List<ArticleData>>
    suspend fun getArticleById(articleID: String): ArticleData
    suspend fun getArticleNeighbors(articleId: String, sectionID: String): Bundle

}