package com.traiden.fetchtrendingrepo.presentation

import android.content.ClipData.Item
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.common.base.Predicates.instanceOf
import com.traiden.fetchtrendingrepo.domain.Items
import com.traiden.fetchtrendingrepo.domain.NetworkResult
import com.traiden.fetchtrendingrepo.domain.Owner
import com.traiden.fetchtrendingrepo.domain.Repository
import com.traiden.fetchtrendingrepo.domain.usecases.GetTrendingRepositoriesUseCase
import com.traiden.fetchtrendingrepo.presentation.viewmodel.TrendingRepositoriesViewModel
import com.traiden.fetchtrendingrepo.util.MockRepoHelper
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TrendingRepoViewModelTest {

    @MockK
    private lateinit var getTrendingRepositoriesUseCase: GetTrendingRepositoriesUseCase

    private lateinit var trendingRepositoriesViewModel: TrendingRepositoriesViewModel
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
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


            //Mockito.`when`(getTrendingRepositoriesUseCase.execute()).thenReturn(MockRepoHelper.getMockRepo())

            coEvery {
                getTrendingRepositoriesUseCase.execute()
            }.returns(MockRepoHelper.getMockRepo())


            val observer = mockk<Observer<NetworkResult<Items>>> { every { onChanged(any()) } just Runs }

            //val liveDataObserver = mock(Observer::class.java) as Observer<NetworkResult<Items>>

            trendingRepositoriesViewModel.repositories.observeForever(observer)
            // When
            trendingRepositoriesViewModel.fetchTrendingRepositories()

            coVerify {
                getTrendingRepositoriesUseCase.execute()
            }
                //verify(liveDataObserver).onChanged(NetworkResult.Loading())
                //verify(liveDataObserver).onChanged(NetworkResult.Success(MockRepoHelper.getMockRepo()))
            }
        }


    @Test
    fun `fetchTrendingRepositoriesReturnEmptyList`() {
        runBlocking {
            // Given
            coEvery {
                getTrendingRepositoriesUseCase.execute()
            }.returns(Items(emptyList()))

            // When
            trendingRepositoriesViewModel.fetchTrendingRepositories()

            coVerify {
                getTrendingRepositoriesUseCase.execute()
            }

            // Then
            val liveDataObserver = mock(Observer::class.java) as Observer<NetworkResult<Items>>
            trendingRepositoriesViewModel.repositories.observeForever(liveDataObserver)
            Assert.assertEquals(trendingRepositoriesViewModel.repositories.value as NetworkResult<Items>,NetworkResult.Success(Items(
                emptyList()
            )))
            //verify(liveDataObserver).onChanged(NetworkResult.Success(Items(emptyList())))
        }
    }


    @Test
    fun `fetchTrendingRepositoriesReturnSortedList`() {
        runBlocking {
            // Given
            coEvery {
                getTrendingRepositoriesUseCase.execute()
            }.returns(MockRepoHelper.getFilteredList())

            // When
            trendingRepositoriesViewModel.fetchTrendingRepositories()

            coVerify {
                getTrendingRepositoriesUseCase.execute()
            }

            // Then
            val liveDataObserver = mock(Observer::class.java) as Observer<NetworkResult<Items>>
            trendingRepositoriesViewModel.repositories.observeForever(liveDataObserver)
            Assert.assertEquals(trendingRepositoriesViewModel.repositories.value as NetworkResult<Items>,
                NetworkResult.Success(MockRepoHelper.getFilteredList()))
        }
    }



}