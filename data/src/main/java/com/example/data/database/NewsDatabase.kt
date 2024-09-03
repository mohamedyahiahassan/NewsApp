package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.model.ArticlesItemDto
import com.example.data.model.RoomTypeConverters

@Database(entities = [ArticlesItemDto::class], version = 1, exportSchema = true)
@TypeConverters(value = [RoomTypeConverters::class])
abstract class NewsDatabase:RoomDatabase() {

    abstract fun NewsDao(): NewsDao

    companion object{

        private var instance:NewsDatabase? = null

        fun init(context: Context){
            if (instance == null) {

                //initialize

                instance = Room.databaseBuilder(
                        context,
                        NewsDatabase::class.java,
                        "newsDB")
                        .fallbackToDestructiveMigration()
                        .build()


                }

            }

            fun getInstance(): NewsDatabase {

                return instance!!
            }
        }
}