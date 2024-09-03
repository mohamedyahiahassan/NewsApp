package com.example.domain.repository



import com.example.data.utils.Resource
import com.example.domain.model.ArticlesItem
import com.example.domain.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(categoryName: String): Flow<Resource<List<ArticlesItem>>>
}