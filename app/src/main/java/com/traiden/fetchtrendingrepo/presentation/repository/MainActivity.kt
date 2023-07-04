package com.traiden.fetchtrendingrepo.presentation.repository


import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.traiden.fetchtrendingrepo.R
import com.traiden.fetchtrendingrepo.base.BaseActivity
import com.traiden.fetchtrendingrepo.base.MyApp.Companion.isLoading
import com.traiden.fetchtrendingrepo.presentation.viewmodel.TrendingRepositoriesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel: TrendingRepositoriesViewModel by viewModels()

    private lateinit var adapter: RepoAdapter
    private lateinit var recylerview: RecyclerView
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recylerview = findViewById(R.id.rv_repositories)
        shimmerFrameLayout = findViewById(R.id.shimmerLayout)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recylerview.layoutManager = llm
        observeViewModel()
        viewModel.fetchTrendingRepositories()
    }

    private fun observeViewModel() {
        viewModel.repositories.observe(this) { repositories ->
            // Update the UI with the fetched repositories
            adapter = RepoAdapter(this,repositories.items)
            recylerview.adapter = adapter
        }

        viewModel.loadAnimation.observe(this) { isLoading ->
            // Update the UI with the fetched repositories
            if (isLoading) {
                // Start shimmer animation
                shimmerFrameLayout.startShimmer()
                recylerview.visibility = View.GONE
            } else {
                // Stop shimmer animation
                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.visibility = View.GONE

                // Show the fetched data in the RecyclerView
                recylerview.visibility = View.VISIBLE
            }
        }
    }
}