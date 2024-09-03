package com.example.data.Api

import com.example.data.model.NewsResponseDto
import com.example.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WebServices {


@GET ("top-headlines/category/{categoryName}/us.json")
suspend fun getArticles(
    @Path("categoryName")categoryName:String
): NewsResponseDto



}