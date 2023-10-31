package com.speedyy.newsapp.util

import com.speedyy.newsapp.model.News
import com.speedyy.newsapp.model.NewsSource

fun transformNewsToTable(news: News): com.speedyy.newsapp.roomdb.News {
    val author = news.author ?: ""
    val newsDb = com.speedyy.newsapp.roomdb.News(null,news.title, news.description, news.urlToImage,author,news.publishedAt, news.source.name)
    return newsDb
}

fun transformNewsToModel(news: com.speedyy.newsapp.roomdb.News): com.speedyy.newsapp.model.News {
    val author = news.author ?: ""
    val newsSource = NewsSource(news.name)
    val newsDb = News(author, news.title, news.publishedAt, news.desc, news.imgURL, newsSource)
    return newsDb
}





