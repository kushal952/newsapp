package com.speedyy.newsapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface NewsDAO {

    @Insert
    suspend fun insertNews(news: News)

    @Query("SELECT * FROM news_data")
    fun getAllNews(): LiveData<List<News>>

}





