package com.traiden.fetchtrendingrepo.presentation.repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.traiden.fetchtrendingrepo.R
import com.traiden.fetchtrendingrepo.presentation.viewmodel.TrendingRepositoriesViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: TrendingRepositoriesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeViewModel()
        viewModel.fetchTrendingRepositories()
    }

    private fun observeViewModel() {
        viewModel.repositories.observe(this) { repositories ->
            // Update the UI with the fetched repositories
        }
    }
}