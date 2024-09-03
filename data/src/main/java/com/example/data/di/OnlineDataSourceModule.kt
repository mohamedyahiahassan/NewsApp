package com.example.data.di

import com.example.data.dataSource.OnlineDataSourceImpl
import com.example.data.repository.NewsRepositoryImpl
import com.example.domain.dataSource.OnlineDataSource
import com.example.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object OnlineDataSourceModule {

    @Provides
    fun provideNewsRepo(impl: OnlineDataSourceImpl): OnlineDataSource {

        return impl
    }
}