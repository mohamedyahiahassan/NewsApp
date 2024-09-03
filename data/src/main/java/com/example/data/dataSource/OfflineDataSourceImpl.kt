package com.example.data.dataSource

import com.example.data.database.NewsDatabase
import com.example.data.model.ArticlesItemDto
import com.example.data.model.SourceDto
import com.example.data.utils.Resource
import com.example.domain.dataSource.OfflineDataSource
import com.example.domain.model.ArticlesItem
import com.example.domain.model.NewsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OfflineDataSourceImpl @Inject constructor(private val newsDatabase: NewsDatabase): OfflineDataSource {

    override suspend fun saveNews(articleList: List<ArticlesItem>) {

        newsDatabase.NewsDao().saveNews(articleList.map {

            ArticlesItemDto(
                publishedAt = it.publishedAt,
                author= it.author,
                urlToImage= it.urlToImage,
                description= it.description,
                source= SourceDto(it.source?.name,it.source?.id),
                title= it.title,
                url= it.url,
                content=it.content
                )
        })
    }

    override suspend fun getNews(): Flow<Resource<List<ArticlesItem>>>  = flow{

        emit(Resource.Loading())

        val articlesList = mutableListOf<ArticlesItem>()

        val result = try {

            val response = newsDatabase.NewsDao().getNews()

            response.forEach {

                articlesList.add(it.toArticle())
            }

            Resource.Success(articlesList.toList())

        }catch (e:Exception){

            Resource.Error(e.message.toString())

        }

        emit(result)
    }

    override suspend fun delete() {

        newsDatabase.NewsDao().delete()
    }
}