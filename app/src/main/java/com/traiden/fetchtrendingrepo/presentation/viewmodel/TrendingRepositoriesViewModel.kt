package com.traiden.fetchtrendingrepo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.traiden.fetchtrendingrepo.domain.Items
import com.traiden.fetchtrendingrepo.domain.NetworkResult
import com.traiden.fetchtrendingrepo.domain.usecases.GetTrendingRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingRepositoriesViewModel @Inject constructor(private val getTrendingRepositoriesUseCase: GetTrendingRepositoriesUseCase) : ViewModel() {
    private val _repositories = MutableLiveData<NetworkResult<Items>>()
    val repositories: LiveData<NetworkResult<Items>> = _repositories

    fun fetchTrendingRepositories() {
        viewModelScope.launch {
            try {
                _repositories.value = NetworkResult.Loading()
                val repositories = getTrendingRepositoriesUseCase.execute()
                _repositories.value = NetworkResult.Success(repositories)
            } catch (e: Exception) {
                Log.d(
                    TrendingRepositoriesViewModel::class.simpleName,
                    "fetchTrendingRepositories: ${e.printStackTrace()}"
                )
                _repositories.value = NetworkResult.Error()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}