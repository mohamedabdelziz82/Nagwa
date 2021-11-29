package com.mohamedabdelaziz.nagwatask.presentation.ui

import dagger.hilt.android.AndroidEntryPoint
import com.mohamedabdelaziz.nagwatask.core.base.BaseActivity
import com.mohamedabdelaziz.nagwatask.presentation.viewmodels.ContentViewModel
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamedabdelaziz.nagwatask.R
import com.mohamedabdelaziz.nagwatask.domain.entities.Content
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.mohamedabdelaziz.nagwatask.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isConnectedForMenu = false
    private val trendingViewModel by viewModels<ContentViewModel>()

    @Inject
    lateinit var adapter: ContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()

        binding.contentSwipeRefresh.setOnRefreshListener {
            if (isConnectedForMenu) getContents()
            binding.contentSwipeRefresh.isRefreshing = false
        }
        binding.retryBtn.setOnClickListener {
            if (isConnectedForMenu) {
                visibleTrendRecyclerView()
                getContents()
            } else visibleNoInternet()
        }

    }

    override
    fun onConnectionSuccess() {
        visibleNoInternet()
        isConnectedForMenu = true
        visibleTrendRecyclerView()
    }

    override
    fun onConnectionFailed() {
        isConnectedForMenu = false
        visibleNoInternet()
    }

    private fun visibleTrendRecyclerView() {
        binding.noInternetConstraintLayout.visibility = View.GONE
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.contentRecyclerView.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
        getContents()
    }

    private fun visibleNoInternet() {
        binding.noInternetConstraintLayout.visibility = View.VISIBLE
        binding.shimmerLayout.visibility = View.GONE
        binding.contentRecyclerView.visibility = View.GONE
    }

    private fun init() {
        binding.contentRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.contentSwipeRefresh.setColorSchemeResources(R.color.black)
        setSupportActionBar(binding.contentsToolbar)
        binding.contentRecyclerView.adapter = adapter
    }

    private fun getContents() {
        binding.shimmerLayout.startShimmer()
        trendingViewModel.contentsMutableLiveData.observe(
            this,
            { contentsListResponse: List<Content> ->
                adapter.submitList(contentsListResponse)
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
            })
    }


}