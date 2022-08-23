package com.example.beritalengkap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val mData = ArrayList<News>()
    private var onItemClickCallback: OnItemClickCallback? =null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: ArrayList<News>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): NewsViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.news_row_item, viewGroup, false)
        return NewsViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(newsViewHolder: NewsViewHolder, position: Int) {
        newsViewHolder.bind(mData[position])
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {
            with(itemView){
                Glide.with(itemView.context)
                    .load(news.photo)
                    .apply(RequestOptions().override(55, 55))
                    .into(itemImageView)


                newsAuthor.text = news.title

                newsTitle.text = news.description

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(news) }
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: News)
    }
}