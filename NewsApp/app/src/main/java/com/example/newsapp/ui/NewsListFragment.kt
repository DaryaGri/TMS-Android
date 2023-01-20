package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.ui.adapters.NewsPagerAdapter
import com.example.newsapp.viewModels.MainNewsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainNewsListViewModel by viewModels()
    private val adapter = NewsPagerAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpMenu()
        setUpRecycler()
        setUpObservers()

    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            viewModel.getNewsList().observe(viewLifecycleOwner) {
                it.let {
                    adapter.submitData(lifecycle, it)
                }
            }
            adapter.loadStateFlow.collect {
                binding.MainNewsProgressBar.isVisible = it.source.refresh is LoadState.Loading
            }
        }

        viewModel.ifProgressBarVisible.observe(viewLifecycleOwner) { visible ->
            if (visible) {
                binding.MainNewsProgressBar.visibility = View.VISIBLE
            } else {
                binding.MainNewsProgressBar.visibility = View.GONE
            }
        }
    }

    private fun setUpRecycler() {
        binding.newsRecycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.newsRecycleView.adapter = adapter
    }

    private fun setUpMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.settings -> {
                        Log.d("TAG", "Settings selected")
                        viewModel.cityPref.value = "us"
                        true
                    }
                    R.id.favourite -> {
                        Log.d("TAG", "Favourite selected ")
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
