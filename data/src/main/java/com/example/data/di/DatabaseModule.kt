package com.example.data.di

import com.example.data.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideNewsDatabase():NewsDatabase{

        return NewsDatabase.getInstance()
    }
}