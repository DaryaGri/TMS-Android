package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.data.Articles
import com.example.newsapp.data.DbArticles
import com.example.newsapp.data.Source
import com.example.newsapp.databinding.FragmentFavouriteNewsBinding
import com.example.newsapp.ui.adapters.FavNewsAdapter
import com.example.newsapp.viewModels.FavouriteNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteNewsFragment : Fragment() {

    private var _binding: FragmentFavouriteNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouriteNewsViewModel by viewModels()

    private lateinit var adapter: FavNewsAdapter
    private lateinit var job: Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        setUpObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecycler() {
        adapter = FavNewsAdapter { article ->
            viewModel.deleteArticle(article)
        }

        binding.favNewsRecycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.favNewsRecycleView.adapter = adapter
    }

    private fun setUpObservers() {
        job = lifecycleScope.launch {
            viewModel.getAll().collect() { data ->
                val list = convertList(data)
                adapter.differ.submitList(list)
            }
        }
    }

    private fun convertList(data: List<DbArticles>): MutableList<Articles> {
        val result = mutableListOf<Articles>()
        for (elem in data) {
            result.add(
                Articles(
                    Source("", elem.source),
                    elem.author,
                    elem.title,
                    elem.description,
                    elem.url,
                    elem.urlToImage,
                    "",
                    ""
                )
            )
        }
        return result
    }
}