package com.example.data.di

import com.example.data.Api.WebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Provides
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{

        return HttpLoggingInterceptor{

        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient{

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun  provideGsonConverter():GsonConverterFactory{

        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): WebServices {

        return Retrofit.Builder()
            .baseUrl("https://saurav.tech/NewsAPI/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebServices::class.java)
    }
}