package com.example.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.domain.model.ArticlesItem
import com.example.domain.model.NewsResponse
import com.example.domain.model.Source
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class NewsResponseDto(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItemDto?>? = null,

	@field:SerializedName("status")
	val status: String? = null
){

	fun toNewsResponse():NewsResponse{

		val list = mutableListOf<ArticlesItem>()

		articles?.forEach {

			it?.toArticle()?.let { it1 -> list.add(it1) }

		}

		return NewsResponse(totalResults,list,status)
	}
}


data class ArticlesItemDto(


	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val source: SourceDto? = null,

	val sourceName:String?=null,

	val sourceId:String?=null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("content")
	val content: String? = null
){

	fun toArticle():ArticlesItem{

		return ArticlesItem(publishedAt, author, urlToImage, description,source?.toSource(),title, url, content)
	}
}

data class SourceDto(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
){

	fun toSource(): Source{

		return Source(name, id)
	}
}


