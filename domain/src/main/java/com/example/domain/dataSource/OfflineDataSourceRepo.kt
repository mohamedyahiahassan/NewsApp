package com.example.domain.dataSource

import com.example.domain.model.ArticlesItem

interface OfflineDataSourceRepo {

    suspend fun saveNews(categoryName: String,articleList: List<ArticlesItem>)

    suspend fun getNews(categoryName: String): List<ArticlesItem>?

    suspend fun delete()
}