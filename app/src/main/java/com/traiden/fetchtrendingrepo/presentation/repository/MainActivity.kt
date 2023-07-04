package com.traiden.fetchtrendingrepo.presentation.repository


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.traiden.fetchtrendingrepo.R
import com.traiden.fetchtrendingrepo.base.BaseActivity
import com.traiden.fetchtrendingrepo.databinding.ActivityMainBinding
import com.traiden.fetchtrendingrepo.domain.Items
import com.traiden.fetchtrendingrepo.domain.NetworkResult
import com.traiden.fetchtrendingrepo.presentation.viewmodel.TrendingRepositoriesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {
    private val viewModel: TrendingRepositoriesViewModel by viewModels()
    private lateinit var adapter: RepoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun setupViews() {
        Log.d(MainActivity::class.simpleName, "setupViews: ")
        binding = (getBinding() as ActivityMainBinding)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.rvRepositories.layoutManager = llm
        observeViewModel()
        viewModel.fetchTrendingRepositories()
    }

    private fun observeViewModel() {
        viewModel.repositories.observe(this) { networkResult ->
            when(networkResult){
                is NetworkResult.Loading ->{
                    startShimmerAnimation()
                }
                is NetworkResult.Error ->{
                    stopShimmerAnimation()
                }
                is NetworkResult.Success ->{
                    // Stop shimmer animation
                    stopShimmerAnimation()
                    setDataOnRecyclerView(networkResult)
                }

            }

        }
    }

    private fun setDataOnRecyclerView(networkResult: NetworkResult<Items>) {
        // Show the fetched data in the RecyclerView
        binding.rvRepositories.visibility = View.VISIBLE
        // Update the UI with the fetched repositories
        val respositories = networkResult.data as Items
        adapter = RepoAdapter(this, respositories.items)
        binding.rvRepositories.adapter = adapter
    }

    private fun startShimmerAnimation() {
        Log.d(MainActivity::class.simpleName, "startShimmerAnimation: ")
        // Start shimmer animation
        binding.shimmerLayout.startShimmer()
        binding.rvRepositories.visibility = View.GONE
    }

    private fun stopShimmerAnimation() {
        Log.d(MainActivity::class.simpleName, "stopShimmerAnimation: ")
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
    }
}