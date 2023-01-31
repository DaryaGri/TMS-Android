package com.example.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.Articles
import com.example.newsapp.databinding.NewsListItemBinding

class NewsPagerAdapter() :
    PagingDataAdapter<Articles, NewsPagerAdapter.ViewHolder>(NewsComparator) {

    inner class ViewHolder(private val binding: NewsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Articles) {
            if (item != null) {
                with(binding) {
                    Glide.with(this@ViewHolder.itemView.context)
                        .load(item.urlToImage)
                        .placeholder(R.drawable.no_image)
                        .into(newsImageImageView)

                    titleTextView.text = item.title
                    authorTextView.text = item.author
                    mainNewsDescriptionTextView.text = item.description

                    authorTextView.visibility = View.VISIBLE
                    newsImageImageView.visibility = View.VISIBLE
                    mainNewsDescriptionTextView.visibility = View.VISIBLE

                    if (item.author.isNullOrEmpty()){
                        authorTextView.visibility = View.GONE
                    }
                    if (item.urlToImage.isNullOrEmpty()){
                        newsImageImageView.visibility = View.GONE
                    }
                    if (item.description.isNullOrEmpty()){
                        mainNewsDescriptionTextView.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    object NewsComparator : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }
    }

}