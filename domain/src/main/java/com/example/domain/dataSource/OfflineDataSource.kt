package com.example.domain.dataSource

import com.example.data.utils.Resource
import com.example.domain.model.ArticlesItem
import com.example.domain.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface OfflineDataSource {

    suspend fun saveNews(articleList: List<ArticlesItem>)

    suspend fun getNews(): Flow<Resource<List<ArticlesItem>>>

    suspend fun delete()
}