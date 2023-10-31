package com.speedyy.newsapp.model

data class News(val author:String, val title:String, val publishedAt:String,
                val description:String, val urlToImage:String, val source:NewsSource)
