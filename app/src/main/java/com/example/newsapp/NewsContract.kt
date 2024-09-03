package com.example.newsapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import com.example.domain.model.ArticlesItem
import kotlinx.coroutines.flow.StateFlow


class NewsContract {

    interface ViewModel{

        fun doAction(action:Action)

        val states: StateFlow<States>

    }

    sealed interface Action{

        data class LoadNewsList(val categoryName:String):Action
        data class OpenNews(val article:ArticlesItem): Action
        data object GoBack: Action

    }

    sealed interface States{

        data object Initial:States

        data object Loading:States

        data class Error(val message:String):States

        data class Success(val articlesList: List<ArticlesItem?>?):States


    }

}
