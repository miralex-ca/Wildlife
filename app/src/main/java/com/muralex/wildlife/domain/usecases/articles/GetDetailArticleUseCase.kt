package com.muralex.wildlife.domain.usecases.articles

import com.muralex.wildlife.app.utils.Constants.DETAIL_NEXT_KEY
import com.muralex.wildlife.app.utils.Constants.DETAIL_PREV_KEY
import com.muralex.wildlife.app.utils.Dispatchers
import com.muralex.wildlife.app.utils.SettingsManager
import com.muralex.wildlife.domain.data.article.DetailArticleUI
import com.muralex.wildlife.domain.mappers.ArticleDataToArticleMapper
import com.muralex.wildlife.domain.repositories.ArticlesRepository
import kotlinx.coroutines.async
import javax.inject.Inject

class GetDetailArticleUseCase @Inject constructor(
    private val repository: ArticlesRepository,
    private val mapper: ArticleDataToArticleMapper,
    private val settingsManager: SettingsManager,
    private val dispatcher: Dispatchers,

    ) {
    suspend operator fun invoke(articleId: String, sectionID: String) = dispatcher.background {
        val displayNavigation = settingsManager.isDetailNavigationEnabled()
        val neighborsAsync = async {repository.getArticleNeighbors(articleId, sectionID)}
        val articleAsync = async { repository.getArticleById(articleId)}

        val neighbors = neighborsAsync.await()
        val article = articleAsync.await()

        DetailArticleUI(
            article = mapper.mapFromEntity(article),
            sectionId = sectionID,
            previousId = neighbors.getString(DETAIL_PREV_KEY),
            nextId = neighbors.getString(DETAIL_NEXT_KEY),
            displayNavigation = displayNavigation
        )
    }
}