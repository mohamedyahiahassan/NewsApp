package com.example.newsapp

import android.app.Application
import com.example.data.database.NewsDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApp:Application() {

    override fun onCreate() {
        super.onCreate()

        NewsDatabase.init(this)
    }
}