package com.example.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.data.utils.Resource
import com.example.domain.dataSource.OnlineDataSource
import com.example.domain.model.ArticlesItem
import com.example.domain.model.NewsResponse
import com.example.domain.repository.NewsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val onlineDataSource: OnlineDataSource,
    @ApplicationContext context: Context):NewsRepository {


    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = connectivityManager.activeNetwork

    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)




    override suspend fun getNews(categoryName: String): Flow<Resource<List<ArticlesItem>>> {

        if (networkCapabilities != null &&
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        ) {

            var news = onlineDataSource.getNews(categoryName)

            if (!=null){

            }
            return

        }else{

        }


    }
}