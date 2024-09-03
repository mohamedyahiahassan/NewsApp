package com.example.data.di

import com.example.data.dataSource.OnlineDataSourceRepoImpl
import com.example.domain.dataSource.OnlineDataSourceRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object OnlineDataSourceModule {

    @Provides
    fun provideOnlineDataSource(impl: OnlineDataSourceRepoImpl): OnlineDataSourceRepo {

        return impl
    }
}