package com.traiden.fetchtrendingrepo.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.traiden.fetchtrendingrepo.domain.Repository
import com.traiden.fetchtrendingrepo.domain.usecases.GetTrendingRepositoriesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TrendingRepoViewModelTest {


    @Mock
    private lateinit var getTrendingRepositoriesUseCase: GetTrendingRepositoriesUseCase

    private lateinit var trendingRepositoriesViewModel: TrendingRepositoriesViewModel
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    private fun setup() {
        trendingRepositoriesViewModel = TrendingRepositoriesViewModel(getTrendingRepositoriesUseCase)
    }

    @Test
    fun `fetchTrendingRepositories should update repositories LiveData with fetched repositories`() {

            // Given
            val repositories = listOf(
                Repository("Repo 1", "Owner 1", "Description 1", "", "java", 4),
                Repository("Repo 2", "Owner 2", "Description 2", "", "kotlin", 3)
            )
            Mockito.`when`(getTrendingRepositoriesUseCase.execute()).thenReturn(repositories)

            // When
            trendingRepositoriesViewModel.fetchTrendingRepositories()

            // Then
            val liveDataObserver = mock<Observer<List<Repository>>>()
            trendingRepositoriesViewModel.repositories.observeForever(liveDataObserver)
            verify(liveDataObserver).onChanged(repositories)
        }
}