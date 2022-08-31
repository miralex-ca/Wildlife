package com.muralex.navstructure.domain.repositories

import android.os.Bundle
import com.muralex.navstructure.data.model.articles.ArticleData
import com.muralex.navstructure.data.model.structure.SectionData
import com.muralex.navstructure.data.model.structure.SectionDataWithArticles
import com.muralex.navstructure.data.model.structure.SectionDataWithChildren
import com.muralex.navstructure.domain.data.navstructure.SectionWithChildren
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    suspend fun getSectionDataWithArticles(parentID: String): Flow<SectionDataWithArticles>
    fun getArticlesList(sectionID: String): Flow<List<ArticleData>>
    suspend fun getArticleById(articleID: String): ArticleData
    suspend fun getArticleNeighbors(articleId: String, sectionID: String): Bundle

}