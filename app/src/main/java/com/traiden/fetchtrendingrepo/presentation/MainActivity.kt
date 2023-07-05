package com.traiden.fetchtrendingrepo.presentation.repository


import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.traiden.fetchtrendingrepo.R
import com.traiden.fetchtrendingrepo.base.BaseActivity
import com.traiden.fetchtrendingrepo.databinding.ActivityMainBinding
import com.traiden.fetchtrendingrepo.domain.Items
import com.traiden.fetchtrendingrepo.domain.NetworkResult
import com.traiden.fetchtrendingrepo.presentation.viewmodel.TrendingRepositoriesViewModel
import com.traiden.fetchtrendingrepo.util.SharedPreferences
import com.traiden.fetchtrendingrepo.util.gone
import com.traiden.fetchtrendingrepo.util.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {
    private val viewModel: TrendingRepositoriesViewModel by viewModels()
    private lateinit var adapter: RepoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun setupViews() {
        Log.d(MainActivity::class.simpleName, "setupViews: ")
        binding = (getBinding() as ActivityMainBinding)
        observeViewModel()
        viewModel.fetchTrendingRepositories()
        binding.retryLayout.findViewById<Button>(R.id.btnRetry).setOnClickListener {
            viewModel.fetchTrendingRepositories()
        }
    }

    private fun observeViewModel() {
        viewModel.repositories.observe(this) { networkResult ->
            when(networkResult){
                is NetworkResult.Loading ->{
                    startShimmerAnimation()
                    hideRetryLayout()
                }
                is NetworkResult.Error ->{
                    stopShimmerAnimation()
                    showRetryLayout()
                }
                is NetworkResult.Success ->{
                    // Stop shimmer animation
                    hideRetryLayout()
                    stopShimmerAnimation()
                    setDataOnRecyclerView(networkResult)
                }

            }

        }
    }

    private fun showRetryLayout() {
        Log.d(MainActivity::class.simpleName, "showRetryLayout: ")
        binding.retryLayout.visible()
    }

    private fun hideRetryLayout() {
        Log.d(MainActivity::class.simpleName, "showRetryLayout: ")
        binding.retryLayout.gone()
    }

    private fun setDataOnRecyclerView(networkResult: NetworkResult<Items>) {
        // Show the fetched data in the RecyclerView
        binding.rvRepositories.visible()
        // Update the UI with the fetched repositories
        val respositories = networkResult.data as Items
        adapter = RepoAdapter(this, respositories.items)
        binding.rvRepositories.adapter = adapter
    }

    private fun startShimmerAnimation() {
        Log.d(MainActivity::class.simpleName, "startShimmerAnimation: ")
        // Start shimmer animation
        binding.shimmerLayout.startShimmer()
        binding.rvRepositories.gone()
    }

    private fun stopShimmerAnimation() {
        Log.d(MainActivity::class.simpleName, "stopShimmerAnimation: ")
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.gone()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.change_theme -> { // Do whatever you want to do on logout click.
                toggleDarkTheme()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toggleDarkTheme() {
        Log.d(MainActivity::class.simpleName, "toggleDarkTheme: ")
        when (SharedPreferences.getAppTheme(this)) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                SharedPreferences.saveAppTheme(this,AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                SharedPreferences.saveAppTheme(this,AppCompatDelegate.MODE_NIGHT_NO)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}