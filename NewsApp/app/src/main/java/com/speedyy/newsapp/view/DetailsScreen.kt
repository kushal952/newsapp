package com.speedyy.newsapp.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.speedyy.newsapp.R
import com.speedyy.newsapp.databinding.DetailsScreenBinding

class DetailsScreen:Fragment(R.layout.details_screen) {

    private lateinit var detailsScreenBinding: DetailsScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsScreenBinding = DetailsScreenBinding.bind(view)
        val newsItem = arguments
        val author = newsItem?.getString("author")
        val urlToImage = newsItem?.getString("urlToImage")
        val publishedAt = newsItem?.getString("publishedAt")
        val description = newsItem?.getString("description")
        val title = newsItem?.getString("title")
        val name = newsItem?.getString("name")

        Glide.with(requireContext()).load(urlToImage).into(detailsScreenBinding.imageView)
        detailsScreenBinding.authorTv.text = author
        detailsScreenBinding.newsDateTv.text = publishedAt
        detailsScreenBinding.newsDescTv.text = description
        detailsScreenBinding.titleNewsTv.text = title
        detailsScreenBinding.nameNewsTv.text = name

        detailsScreenBinding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}