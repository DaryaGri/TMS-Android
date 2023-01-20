package com.example.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.Articles
import com.example.newsapp.databinding.NewsListItemBinding

class NewsPagerAdapter() :
    PagingDataAdapter<Articles, NewsPagerAdapter.ViewHolder>(NewsComparator) {

    inner class ViewHolder(private val binding: NewsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Articles) {
            if (item != null) {
                with(binding) {
                    Glide.with(this@ViewHolder.itemView.context).load(item.urlToImage
                        ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRucpvmQPFPFFQ2PomrKQh9zw7AV_OROfs6pg&usqp=CAU")
                        .into(newsImageImageView)
                    titleTextView.text = item.title
                    authorTextView.text = item.author
                    mainNewsDescriptionTextView.text = item.description
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