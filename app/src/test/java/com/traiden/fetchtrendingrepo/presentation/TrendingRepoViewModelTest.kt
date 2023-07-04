package com.traiden.fetchtrendingrepo.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.traiden.fetchtrendingrepo.domain.Items
import com.traiden.fetchtrendingrepo.domain.NetworkResult
import com.traiden.fetchtrendingrepo.domain.Owner
import com.traiden.fetchtrendingrepo.domain.Repository
import com.traiden.fetchtrendingrepo.domain.usecases.GetTrendingRepositoriesUseCase
import com.traiden.fetchtrendingrepo.presentation.viewmodel.TrendingRepositoriesViewModel
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
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
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        trendingRepositoriesViewModel = TrendingRepositoriesViewModel(getTrendingRepositoriesUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchTrendingRepositories should update repositories LiveData with fetched repositories`() {

        runBlocking {
            // Given
            val repositories = Items(listOf(Repository("Repo 1", Owner("","OwnerName"), "Description 1", "", 4),
                Repository("Repo 2", Owner("","OwnerName2"), "Description 2", "", 5)))

            Mockito.`when`(getTrendingRepositoriesUseCase.execute()).thenReturn(repositories)

            // When
            trendingRepositoriesViewModel.fetchTrendingRepositories()

            // Then
            val liveDataObserver = mock(Observer::class.java) as Observer<NetworkResult<Items>>
            trendingRepositoriesViewModel.repositories.observeForever(liveDataObserver)
            verify(liveDataObserver).onChanged(NetworkResult.Success(repositories))
        }
    }
}