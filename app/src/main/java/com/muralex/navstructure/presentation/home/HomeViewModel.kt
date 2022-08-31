package com.muralex.navstructure.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muralex.navstructure.domain.usecases.structure.GetSectionsListUseCase
import com.muralex.navstructure.presentation.home.HomeContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSectionsListUseCase: GetSectionsListUseCase,
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    private val _viewEffect: Channel<ViewEffect> = Channel()
    val viewEffect = _viewEffect.receiveAsFlow()

    fun setIntent(intent: ViewIntent) {
        val action = HomeContract.intentToAction(intent)
        handleModelAction(action)
    }

    private fun setEffect(effect: ViewEffect) = viewModelScope.launch {
        _viewEffect.send(effect)
    }

    private fun handleModelAction(modelAction: ModelAction) {
        when (modelAction) {
            is ModelAction.GetSections -> getData()
            is ModelAction.Navigate -> setEffect(ViewEffect.Navigate(modelAction.section))
        }
    }

    private fun getData() = viewModelScope.launch(Dispatchers.IO) {
        val list = getSectionsListUseCase()
        if (list.isEmpty()) _viewState.postValue(ViewState.EmptyList)
        else _viewState.postValue(ViewState.ListLoaded(list))
    }


}

