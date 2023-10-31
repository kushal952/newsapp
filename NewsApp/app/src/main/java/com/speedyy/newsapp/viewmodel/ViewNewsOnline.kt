package com.speedyy.newsapp.viewmodel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.speedyy.newsapp.model.News
import com.speedyy.newsapp.model.Resource
import com.speedyy.newsapp.repo.Repository
import com.speedyy.newsapp.roomdb.NewsDatabase
import kotlinx.coroutines.launch

class ViewNewsOnline : ViewModel() {

    private val newsData = MutableLiveData<Resource>()
    val newsDataOnline: LiveData<Resource>
        get() = newsData

    private val newsDataDb = MutableLiveData<ArrayList<News>>()
    val newsDataOffline: LiveData<ArrayList<News>>
        get() = newsDataDb

    fun getValuesFromServer(context: Context, viewLifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            val newsDb = NewsDatabase(context)
            val repository = Repository(newsDb)
            newsData.value = repository.getAllNewsFromServer(viewLifecycleOwner)
        }
    }

    fun getValuesFromDb(context: Context, viewLifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            val newsDb = NewsDatabase(context)
            val repository = Repository(newsDb)
            val valuesDb: MutableLiveData<ArrayList<News>> = repository.getAllNewsFromDb(viewLifecycleOwner)
            valuesDb.observe(viewLifecycleOwner) {
                newsDataDb.postValue(valuesDb.value)
            }
        }
    }
}








