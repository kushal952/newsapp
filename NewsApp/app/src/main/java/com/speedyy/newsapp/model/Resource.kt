package com.speedyy.newsapp.model

data class Resource(val result: Result, val data: ArrayList<News>, val errorMsg: String)

enum class Result {
    ERROR,SUCCESS
}
