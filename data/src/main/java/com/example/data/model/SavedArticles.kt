package com.example.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.domain.model.ArticlesItem
import com.example.domain.model.Source
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity
@TypeConverters(RoomTypeConverters::class)
data class SavedArticles(

    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,

    val category:String?=null,


    val articlesList:List<ArticlesItemSaved>?=null

)

data class ArticlesItemSaved(

    val publishedAt: String? = null,

    val author: String? = null,

    val urlToImage: String? = null,

    val description: String? = null,

    val sourceName:String?=null,

    val sourceId:String?=null,

    val title: String? = null,


    val url: String? = null,

    val content: String? = null
){

    fun toArticle(): ArticlesItem {

        return ArticlesItem(publishedAt, author, urlToImage, description,
            Source(sourceName,sourceId),title, url, content)
    }
}

class RoomTypeConverters{
    @TypeConverter
    fun convertArticleListJSONString(articlesList:List<ArticlesItemSaved>?): String? {


        return Gson().toJson(articlesList)
    }
    @TypeConverter
    fun convertJSONStringToArticleList(jsonString: String): List<ArticlesItemSaved>? {


        return  Gson().fromJson(jsonString,object: TypeToken<List<ArticlesItemSaved>?>(){}.type)
    }


}

