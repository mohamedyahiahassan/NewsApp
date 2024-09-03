package com.example.domain.dataSource

import com.example.data.utils.Resource
import com.example.domain.model.ArticlesItem
import kotlinx.coroutines.flow.Flow

interface OnlineDataSourceRepo {

    suspend fun getNews(categoryName: String): Flow<Resource<List<ArticlesItem>>>
}