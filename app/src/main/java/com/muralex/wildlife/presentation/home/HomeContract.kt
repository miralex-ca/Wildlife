package com.muralex.wildlife.presentation.home

import com.muralex.wildlife.domain.data.navstructure.Section
import com.muralex.wildlife.presentation.utils.UiEffect
import com.muralex.wildlife.presentation.utils.UiEvent
import com.muralex.wildlife.presentation.utils.UiIntent
import com.muralex.wildlife.presentation.utils.UiState

class HomeContract {

    sealed class UserAction : UiEvent {
        object LaunchScreen : UserAction()
        data class ListItemClick(val section: Section) : UserAction()
    }

    sealed class ViewIntent : UiIntent {
        object GetData : ViewIntent()
        data class Navigate(val section: Section) : ViewIntent()
    }

    companion object {
        fun intentToAction(intent: ViewIntent): ModelAction {
            return when (intent) {
                is ViewIntent.GetData -> ModelAction.GetSections
                is ViewIntent.Navigate -> ModelAction.Navigate(intent.section)
            }
        }
    }

    sealed class ModelAction {
        object GetSections : ModelAction()
        data class Navigate(val section: Section) : ModelAction()
    }

    sealed class ViewState : UiState {
        object EmptyList : ViewState()
        data class ListLoaded(val data: List<Section>) : ViewState()
    }

    sealed class ViewEffect : UiEffect {
        data class Navigate(val section: Section) : ViewEffect()
    }

}