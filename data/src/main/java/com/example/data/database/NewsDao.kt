package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.ArticlesItemDto
import com.example.data.model.SavedArticles

@Dao
interface NewsDao {

    @Query("SELECT * FROM SavedArticles")
    suspend fun getNews():List<SavedArticles>

    @Query("SELECT * FROM SavedArticles WHERE category=:categoryName ")
    suspend fun getCategoryNews(categoryName: String): SavedArticles

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(savedArticles: List<SavedArticles>)


    @Query("DELETE FROM SavedArticles")
    suspend fun delete():Int
}