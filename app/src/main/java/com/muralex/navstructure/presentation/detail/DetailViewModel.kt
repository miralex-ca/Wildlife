package com.muralex.navstructure.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muralex.navstructure.domain.usecases.articles.GetArticleUseCase
import com.muralex.navstructure.domain.usecases.articles.GetDetailArticleUseCase
import com.muralex.navstructure.presentation.detail.DetailContract.ModelAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getArticleUseCase: GetArticleUseCase,
    private val getDetailArticleUseCase: GetDetailArticleUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<DetailContract.ViewState>()
    val viewState: LiveData<DetailContract.ViewState>
        get() = _viewState

    private val _viewEffect: Channel<DetailContract.ViewEffect> = Channel()
    val viewEffect = _viewEffect.receiveAsFlow()

    fun setIntent(intent: DetailContract.ViewIntent) {
        val action = DetailContract.intentToAction(intent)
        handleModelAction(action)
    }

    private fun setEffect(effect: DetailContract.ViewEffect) = viewModelScope.launch {
        _viewEffect.send(effect)
    }

    private fun handleModelAction(modelAction: ModelAction) {
        when (modelAction) {
            is ModelAction.GetArticle -> getData(modelAction.articleID, modelAction.sectionID)
        }
    }

    private fun getData(articleID: String?, sectionID: String) {
        viewModelScope.launch(Dispatchers.IO) {

            articleID?.let {
                val articleDetail = getDetailArticleUseCase(articleID, sectionID)
                if (articleDetail.article.id.isBlank()) _viewState.postValue(DetailContract.ViewState.Idle)
                else _viewState.postValue(DetailContract.ViewState.ArticleLoaded(articleDetail))
            }

        }
    }

}

