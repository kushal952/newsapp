package com.speedyy.newsapp.api

import com.speedyy.newsapp.BuildConfig
import com.speedyy.newsapp.model.JSONNews
import com.speedyy.newsapp.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/v2/everything")
    suspend fun getNews(
        @Query("q") query: String = "tesla",
        @Query("from") from:String = "2023-10-29",
        @Query("sortBy") sortBy:String = "publishedAt",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("pageSize") pageSize:Int = 10,
        @Query("page") page:Int = 1
    ): Response<JSONNews>

}












