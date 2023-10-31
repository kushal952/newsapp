package com.speedyy.newsapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.speedyy.newsapp.R
import com.speedyy.newsapp.adapters.OnlineNewsAdapter
import com.speedyy.newsapp.databinding.OnlineItemsBinding
import com.speedyy.newsapp.model.Result
import com.speedyy.newsapp.viewmodel.ViewNewsOnline

class ViewOnlineNews: Fragment(R.layout.online_items) {

    private lateinit var viewModelNews: ViewNewsOnline
    private lateinit var viewOnlineNewsBinding: OnlineItemsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelNews = ViewModelProvider(this).get(ViewNewsOnline::class.java)
        viewOnlineNewsBinding = OnlineItemsBinding.bind(view)
        viewModelNews.getValuesFromServer(view.context, viewLifecycleOwner)
        viewModelNews.newsDataOnline.observe(viewLifecycleOwner) {
            if (it?.result == Result.SUCCESS) {
                val clickedNews = it.data
                val adapter = OnlineNewsAdapter(it.data) { view, itemIndex ->
                    val bundle: Bundle? = Bundle()
                    val clickedNews = clickedNews[itemIndex]
                    bundle?.putString("author", clickedNews.author)
                    bundle?.putString("urlToImage", clickedNews.urlToImage)
                    bundle?.putString("publishedAt", clickedNews.publishedAt)
                    bundle?.putString("description", clickedNews.description)
                    bundle?.putString("title", clickedNews.title)
                    bundle?.putString("name", clickedNews.source.name)
                    findNavController().navigate(R.id.action_landing_screen_to_detailsScreen, bundle)
                }
                val layoutRv = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL,false)
                viewOnlineNewsBinding.onlineRv.layoutManager = layoutRv
                viewOnlineNewsBinding.onlineRv.adapter = adapter
                viewOnlineNewsBinding.errorMsgTv.visibility = View.GONE
                viewOnlineNewsBinding.onlineRv.visibility = View.VISIBLE
            } else if(it?.result == Result.ERROR) {
                viewOnlineNewsBinding.errorMsgTv.visibility = View.VISIBLE
                viewOnlineNewsBinding.onlineRv.visibility = View.GONE
            }
            viewOnlineNewsBinding.pullRefresh.isRefreshing = false
        }
        viewOnlineNewsBinding.pullRefresh.setOnRefreshListener {
            viewModelNews.getValuesFromServer(view.context, viewLifecycleOwner)
        }
    }
}












