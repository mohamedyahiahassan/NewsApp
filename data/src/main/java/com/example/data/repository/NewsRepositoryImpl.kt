package com.example.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.data.utils.Resource
import com.example.domain.dataSource.OfflineDataSourceRepo
import com.example.domain.dataSource.OnlineDataSourceRepo
import com.example.domain.model.ArticlesItem
import com.example.domain.repository.NewsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val onlineDataSource: OnlineDataSourceRepo,
    private val offlineDataSource: OfflineDataSourceRepo,
    @ApplicationContext private val context: Context):NewsRepository {

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = connectivityManager.activeNetwork

    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)


    override suspend fun getNews(categoryName: String): Flow<Resource<List<ArticlesItem>>> = flow {

        emit(Resource.Loading())

        if (networkCapabilities != null &&
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        ) {

           onlineDataSource.getNews(categoryName).collect{

               when(it){
                   is Resource.Error -> {

                       emit(Resource.Error(it.message.toString()))
                   }

                   is Resource.Success -> {

                       if (it.data!=null){

                           offlineDataSource.saveNews(categoryName,it.data!!)

                           emit(Resource.Success(it.data!!))
                       }

                   }

                   is Resource.Loading ->{

                   }
               }
           }

        }else{

            val listOfNewsFromDatabase = offlineDataSource.getNews(categoryName)

            if (listOfNewsFromDatabase!=null){

                emit(Resource.Success(listOfNewsFromDatabase))
            }
        }
    }
}