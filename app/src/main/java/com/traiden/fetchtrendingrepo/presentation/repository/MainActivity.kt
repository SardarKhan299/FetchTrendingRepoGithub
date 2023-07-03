package com.traiden.fetchtrendingrepo.presentation.repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.traiden.fetchtrendingrepo.R
import com.traiden.fetchtrendingrepo.base.BaseActivity
import com.traiden.fetchtrendingrepo.presentation.viewmodel.TrendingRepositoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel: TrendingRepositoriesViewModel by viewModels()

    private lateinit var adapter: RepoAdapter
    private lateinit var recylerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recylerview = findViewById(R.id.rv_repositories)
        observeViewModel()
        viewModel.fetchTrendingRepositories()
    }

    private fun observeViewModel() {
        viewModel.repositories.observe(this) { repositories ->
            // Update the UI with the fetched repositories
            adapter = RepoAdapter(this,repositories)
            recylerview.adapter = adapter
        }
    }
}