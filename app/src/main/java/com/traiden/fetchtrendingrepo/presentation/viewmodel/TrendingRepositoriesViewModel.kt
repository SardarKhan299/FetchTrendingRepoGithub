package com.traiden.fetchtrendingrepo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.traiden.fetchtrendingrepo.domain.Repository
import com.traiden.fetchtrendingrepo.domain.usecases.GetTrendingRepositoriesUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class TrendingRepositoriesViewModel (private val getTrendingRepositoriesUseCase: GetTrendingRepositoriesUseCase) : ViewModel() {
    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>> = _repositories

    fun fetchTrendingRepositories() {
        viewModelScope.launch {
            try {
                val repositories = getTrendingRepositoriesUseCase.execute()
                _repositories.value = repositories
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}