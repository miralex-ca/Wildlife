package com.muralex.navstructure.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muralex.navstructure.domain.usecases.articles.GetSectionWithArticlesUseCase
import com.muralex.navstructure.domain.usecases.structure.GetSectionWithChildrenUseCase
import com.muralex.navstructure.presentation.category.CategoryContract.ModelAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val sectionWithArticlesUseCase: GetSectionWithArticlesUseCase,
) : ViewModel() {

    private val _viewState = MutableLiveData<CategoryContract.ViewState>()
    val viewState: LiveData<CategoryContract.ViewState>
        get() = _viewState

    private val _viewEffect: Channel<CategoryContract.ViewEffect> = Channel()
    val viewEffect = _viewEffect.receiveAsFlow()

    fun setIntent(intent: CategoryContract.ViewIntent) {
        val action = CategoryContract.intentToAction(intent)
        handleModelAction(action)
    }

    private fun setEffect(effect: CategoryContract.ViewEffect) = viewModelScope.launch {
        _viewEffect.send(effect)
    }

    private fun handleModelAction(modelAction: ModelAction) {
        when (modelAction) {
            is ModelAction.GetSection -> getData(modelAction.sectionID)
            is ModelAction.Navigate -> setEffect(
                CategoryContract.ViewEffect.Navigate(modelAction.article, modelAction.sectionID)
            )
        }
    }

    private fun getData(sectionID: String) {
        viewModelScope.launch(Dispatchers.IO) {

            sectionWithArticlesUseCase(sectionID).collect { list ->
                if (list.articles.isEmpty()) _viewState.postValue(CategoryContract.ViewState.EmptyList)
                else _viewState.postValue(CategoryContract.ViewState.ListLoaded(list))
            }
        }

    }


}

