package com.speedyy.newsapp.repo

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import com.speedyy.newsapp.BuildConfig
import com.speedyy.newsapp.api.RetrofitAPI
import com.speedyy.newsapp.model.JSONNews
import com.speedyy.newsapp.model.News
import com.speedyy.newsapp.model.Resource
import com.speedyy.newsapp.model.Result
import com.speedyy.newsapp.roomdb.NewsDatabase
import com.speedyy.newsapp.util.transformNewsToModel
import com.speedyy.newsapp.util.transformNewsToTable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository(val newsDb: NewsDatabase) {

    suspend fun getAllNewsFromServer(viewLifecycleOwner: LifecycleOwner): Resource? {
        val httpLoggingInter = HttpLoggingInterceptor()
        httpLoggingInter.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder().addInterceptor(httpLoggingInter).build()

        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        var newsAL = ArrayList<News>();
        var result: Resource? = null

        val newsAPI = retrofit.create(RetrofitAPI::class.java)
        var response:Response<JSONNews>? = null
        try {
            response = newsAPI.getNews()
            if (response.isSuccessful) {
                val newsLt =
                    response.body()!!.articles.filter { it.urlToImage != null && it.author != null }
                newsAL.addAll(newsLt)
                insertNews(newsAL, viewLifecycleOwner)
                return Resource(Result.SUCCESS, newsAL, "")
            } else {
                return Resource(Result.ERROR, newsAL, response.errorBody().toString())
            }
        } catch (e: Exception) {
            return Resource(Result.ERROR, newsAL, response?.errorBody().toString())
        }
    }

    fun getAllNewsFromDb(viewLifecycleOwner: LifecycleOwner): MutableLiveData<ArrayList<com.speedyy.newsapp.model.News>> {
        var mutableDataDb = MutableLiveData<ArrayList<com.speedyy.newsapp.model.News>>()
        Log.i("MYTAG", "getAllNewsFromDb: "+newsDb.newsDAO().getAllNews().value)
        newsDb.newsDAO().getAllNews().observe(viewLifecycleOwner) {
            val dbDataList = ArrayList<News>()
            it.forEach{
                val newNews = transformNewsToModel(it)
                dbDataList.add(newNews)
            }
            mutableDataDb.postValue(dbDataList)
        }
        return mutableDataDb
    }

    suspend fun insertNews(newsAL: ArrayList<News>,viewLifecycleOwner: LifecycleOwner) {
        val newsListTableData: List<com.speedyy.newsapp.model.News>? = getAllNewsFromDb(viewLifecycleOwner)?.value

        if(newsListTableData != null && newsListTableData?.size!! > 0) {
            newsAL?.forEach {
                if (newsListTableData?.contains(it) != true){
                    val news = transformNewsToTable(it)
                    newsDb.newsDAO().insertNews(news)
                }
            }
        } else if(newsListTableData == null) {
            newsAL.forEach {
                    val n = transformNewsToTable(it)
                    newsDb.newsDAO().insertNews(n)
                }
        }
    }
}















