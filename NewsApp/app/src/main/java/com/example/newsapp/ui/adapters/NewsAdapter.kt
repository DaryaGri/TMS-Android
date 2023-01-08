package com.example.newsapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.NewsItemData
import com.example.newsapp.databinding.NewsListItemBinding

class NewsAdapter(private var data: ArrayList<NewsItemData>, val context: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsItemData){
            with(binding){
                Glide.with(context).load(item.imgSrc).into(newsImageImageView)
                titleTextView.text = item.title
                authorTextView.text = item.author
                mainNewsDescriptionTextView.text = item.content
            }
        }
    }

    fun setData(data: ArrayList<NewsItemData>){
        this.data = data
        notifyDataSetChanged()
    }
}