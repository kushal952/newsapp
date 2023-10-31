package com.speedyy.newsapp.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_data")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val desc: String,
    @ColumnInfo(name = "img_url")
    val imgURL: String,
    val author: String,
    @ColumnInfo(name = "published_at")
    val publishedAt: String,
    val name: String,
)


















