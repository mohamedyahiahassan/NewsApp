package com.example.data.dataSource

import android.content.Context
import com.example.data.Api.WebServices
import com.example.data.utils.Resource
import com.example.domain.dataSource.OnlineDataSource
import com.example.domain.model.ArticlesItem
import com.example.domain.model.NewsResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OnlineDataSourceImpl @Inject constructor(
    private val webServices: WebServices):OnlineDataSource {

    override suspend fun getNews(categoryName:String): Flow<Resource<List<ArticlesItem>>> = flow{


        emit(Resource.Loading())

        val articlesList = mutableListOf<ArticlesItem>()

        val result = try {

            val response = webServices.getArticles(categoryName).toNewsResponse()

            response.articles?.forEach {

                if (it != null) {
                    articlesList.add(it)
                }
            }


            Resource.Success(articlesList.toList())

        }catch (e:Exception){

            Resource.Error(e.message.toString())
        }

        emit(result)

    }
}