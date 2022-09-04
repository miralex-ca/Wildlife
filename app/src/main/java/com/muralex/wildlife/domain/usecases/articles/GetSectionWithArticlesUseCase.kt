package com.muralex.wildlife.domain.usecases.articles

import com.muralex.wildlife.app.utils.Dispatchers
import com.muralex.wildlife.app.utils.SettingsManager
import com.muralex.wildlife.domain.data.article.SectionWithArticles
import com.muralex.wildlife.domain.mappers.ArticleDataToArticleMapper
import com.muralex.wildlife.domain.mappers.SectionDataToSectionMapper
import com.muralex.wildlife.domain.repositories.ArticlesRepository
import com.muralex.wildlife.presentation.utils.DelayProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSectionWithArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository,
    private val mapper: SectionDataToSectionMapper,
    private val articlesMapper: ArticleDataToArticleMapper,
    private val dispatcher: Dispatchers,
    private val settingsManager: SettingsManager,
    private val delayProvider: DelayProvider
    ) {
    suspend operator fun invoke(sectionID: String): Flow<SectionWithArticles> = dispatcher.background {

        if (settingsManager.isFirstLaunch()) {
            delayProvider.delayLoading()
            settingsManager.setFirstLaunch(false)
        }

         flow {
            repository.getSectionDataWithArticles(sectionID).collect { sectionData ->
                val section = mapper.mapFromEntity(sectionData.section)
                val articles = articlesMapper.mapFromEntityList(sectionData.articles)
                emit(SectionWithArticles(section, articles))
            }
        }
    }
}