package com.muralex.navstructure.domain.usecases.articles

import com.muralex.navstructure.domain.data.article.SectionWithArticles
import com.muralex.navstructure.domain.mappers.ArticleDataToArticleMapper
import com.muralex.navstructure.domain.mappers.SectionDataToSectionMapper
import com.muralex.navstructure.domain.repositories.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSectionWithArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository,
    private val mapper: SectionDataToSectionMapper,
    private val articlesMapper: ArticleDataToArticleMapper,
) {
    suspend operator fun invoke(sectionID: String): Flow<SectionWithArticles> {
        return flow {
            repository.getSectionDataWithArticles(sectionID).collect { sectionData ->
                val section = mapper.mapFromEntity(sectionData.section)
                val articles = articlesMapper.mapFromEntityList(sectionData.articles)
                emit(SectionWithArticles(section, articles))
            }
        }


    }
}