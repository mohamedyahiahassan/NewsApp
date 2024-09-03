package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.ArticlesItemDto

@Dao
interface NewsDao {

    @Query("SELECT * FROM ArticlesItemDto")
    suspend fun getNews():List<ArticlesItemDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(articlesList: List<ArticlesItemDto>)


    @Query("DELETE FROM ArticlesItemDto")
    suspend fun delete():Int
}