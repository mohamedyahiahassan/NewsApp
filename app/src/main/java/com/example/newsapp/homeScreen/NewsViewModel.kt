package com.example.newsapp.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.utils.Resource
import com.example.domain.model.ArticlesItem
import com.example.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,


):ViewModel(), NewsContract.ViewModel {

     private  val _states = MutableStateFlow<NewsContract.States>(NewsContract.States.Initial)

    override val states: MutableStateFlow<NewsContract.States>
        get() = _states

    override fun doAction(action: NewsContract.Action) {

        when(action){
            NewsContract.Action.GoBack -> {


            }
            is NewsContract.Action.LoadNewsList -> {

                getHeadlines(action.categoryName)
            }

            is NewsContract.Action.OpenNews -> {

                selectedNews = action.article
            }

        }
    }


    var selectedNews:ArticlesItem?=null

    private fun getHeadlines(categoryName:String){

        viewModelScope.launch (Dispatchers.IO){

            newsRepository.getNews(categoryName).collect{

                when(it){
                    is Resource.Error -> {

                        Log.e("error getting headline",it.message.toString())

                        _states.value = NewsContract.States.Error("Error Fetching News")

                    }
                    is Resource.Loading -> {

                        _states.value = NewsContract.States.Loading
                    }
                    is Resource.Success -> {

                        _states.value = NewsContract.States.Success(it.data)

                    }
                }
            }
        }

    }
}