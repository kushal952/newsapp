package com.speedyy.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.speedyy.newsapp.R
import com.speedyy.newsapp.model.News
import com.speedyy.newsapp.repo.Repository

class OnlineNewsAdapter(onlineNewsList: ArrayList<News>,onItemClick: (view:View, index:Int) -> Unit): RecyclerView.Adapter<OnlineNewsAdapter.OnlineNewsViewHolder>() {

    class OnlineNewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private var newsList: ArrayList<News> = onlineNewsList
    val onItemClickListener : (view: View, index:Int) -> Unit = onItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnlineNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        return OnlineNewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: OnlineNewsViewHolder, position: Int) {
        val imgIV = holder.itemView.findViewById<ImageView>(R.id.news_img)
        val authorTv = holder.itemView.findViewById<TextView>(R.id.author_name_tv)
        val titleTv = holder.itemView.findViewById<TextView>(R.id.title_tv)
        val dateTimeTv = holder.itemView.findViewById<TextView>(R.id.date_time_tv)

        holder.itemView.let {
            val news = newsList[position]
            Glide.with(holder.itemView.context).load(news.urlToImage).into(imgIV)
            titleTv.text = news.title
            authorTv.text = news.author
            dateTimeTv.text = news.publishedAt
//            dateTv.text = news.date
//            timeTv.text = news.time
        }
        holder.itemView.setOnClickListener {
            onItemClickListener(it, position)
        }
    }
}


















