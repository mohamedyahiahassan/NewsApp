package com.example.data.dataSource

import com.example.data.database.NewsDatabase
import com.example.data.model.ArticlesItemSaved
import com.example.data.model.SavedArticles
import com.example.domain.dataSource.OfflineDataSourceRepo
import com.example.domain.model.ArticlesItem
import javax.inject.Inject

class OfflineDataSourceRepoImpl @Inject constructor(private val newsDatabase: NewsDatabase): OfflineDataSourceRepo {

    override suspend fun saveNews(categoryName: String,articleList: List<ArticlesItem>) {

        newsDatabase.NewsDao().saveNews(listOf(
            SavedArticles(
            category = categoryName,
            articlesList = articleList.map {

                ArticlesItemSaved(
                    publishedAt = it.publishedAt,
                    author= it.author,
                    urlToImage= it.urlToImage,
                    description= it.description,
                    sourceName =it.source?.name,
                    sourceId = it.source?.id,
                    title= it.title,
                    url= it.url,
                    content=it.content
                )
            }
        )
        ))
    }

    override suspend fun getNews(categoryName: String): List<ArticlesItem>? {

       val list = newsDatabase.NewsDao().getCategoryNews(categoryName)

        if (list!=null){

            val finalList = list.articlesList?.map {

                it.toArticle()
            }

            return finalList
        }else{

            return emptyList()
        }


    }

    override suspend fun delete() {

        newsDatabase.NewsDao().delete()
    }
}

