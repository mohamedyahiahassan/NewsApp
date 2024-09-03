package com.example.data.di

import com.example.data.dataSource.OfflineDataSourceRepoImpl
import com.example.domain.dataSource.OfflineDataSourceRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object OfflineDataSourceModule {

    @Provides
    fun provideOfflineDataSource(impl: OfflineDataSourceRepoImpl): OfflineDataSourceRepo {

        return impl
    }
}