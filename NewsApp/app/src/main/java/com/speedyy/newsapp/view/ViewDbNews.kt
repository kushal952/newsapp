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
import com.speedyy.newsapp.viewmodel.ViewNewsOnline

class ViewDbNews: Fragment(R.layout.online_items) {

    private lateinit var viewModelNews: ViewNewsOnline
    private lateinit var viewDbNewsBinding: OnlineItemsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelNews = ViewModelProvider(this).get(ViewNewsOnline::class.java)
        viewDbNewsBinding = OnlineItemsBinding.bind(view)

        viewModelNews.getValuesFromDb(requireContext(), viewLifecycleOwner)
        viewModelNews.newsDataOffline.observe(viewLifecycleOwner) {
            val adapter = OnlineNewsAdapter(it) { view, itemIndex ->
                val bundle: Bundle? = Bundle()
                    val clickedNews = it[itemIndex]
                    bundle?.putString("author", clickedNews.author)
                    bundle?.putString("urlToImage", clickedNews.urlToImage)
                    bundle?.putString("publishedAt", clickedNews.publishedAt)
                    bundle?.putString("description", clickedNews.description)
                    bundle?.putString("title", clickedNews.title)
                    bundle?.putString("name", clickedNews.source.name)
                    findNavController().navigate(R.id.action_landing_screen_to_detailsScreen, bundle)
            }
            val layoutRv = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL,false)
                viewDbNewsBinding.onlineRv.layoutManager = layoutRv
                viewDbNewsBinding.onlineRv.adapter = adapter
            viewDbNewsBinding.pullRefresh.isRefreshing = false
        }
        viewDbNewsBinding.pullRefresh.setOnRefreshListener {
            viewModelNews.getValuesFromDb(view.context, viewLifecycleOwner)
        }

    }
}







