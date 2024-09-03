package com.example.data.dataSource

import com.example.data.Api.WebServices
import com.example.data.utils.Resource
import com.example.domain.dataSource.OnlineDataSourceRepo
import com.example.domain.model.ArticlesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OnlineDataSourceRepoImpl @Inject constructor(
    private val webServices: WebServices):OnlineDataSourceRepo {

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