package com.traiden.fetchtrendingrepo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.traiden.fetchtrendingrepo.base.MyApp.Companion.isLoading
import com.traiden.fetchtrendingrepo.domain.Items
import com.traiden.fetchtrendingrepo.domain.Repository
import com.traiden.fetchtrendingrepo.domain.usecases.GetTrendingRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingRepositoriesViewModel @Inject constructor(private val getTrendingRepositoriesUseCase: GetTrendingRepositoriesUseCase) : ViewModel() {
    private val _repositories = MutableLiveData<Items>()
    private val _loadAnimations = MutableLiveData<Boolean>()
    val repositories: LiveData<Items> = _repositories
    val loadAnimation: LiveData<Boolean> = _loadAnimations

    fun fetchTrendingRepositories() {
        viewModelScope.launch {
            try {
                _loadAnimations.value = true
                val repositories = getTrendingRepositoriesUseCase.execute()
                _repositories.value = repositories
                _loadAnimations.value = false
            } catch (e: Exception) {
                Log.d(
                    TrendingRepositoriesViewModel::class.simpleName,
                    "fetchTrendingRepositories: ${e.printStackTrace()}"
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}