package com.traiden.fetchtrendingrepo.data.repositories

import com.traiden.fetchtrendingrepo.domain.GithubRepository
import com.traiden.fetchtrendingrepo.domain.Items
import com.traiden.fetchtrendingrepo.domain.Owner
import com.traiden.fetchtrendingrepo.domain.Repository
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GithubRepositoryTest {


    @Mock
    private lateinit var apiService : ApiService
    private lateinit var githubRepository: GithubRepository

    @Before
    fun setup(){
        githubRepository = GithubRepositoryImpl(apiService)
    }

    @Test
    fun fetchTrendingRepositories_should_return_list_of_repositories() {
        runBlocking {
            // Given
            val repositories = Items(listOf(Repository("Repo 1", Owner("","OwnerName"), "Description 1", "", 4),
                Repository("Repo 2", Owner("","OwnerName2"), "Description 2", "", 5)))
            Mockito.`when`(apiService.getTrendingRepositories("")).thenReturn(repositories)

            // When
            val result = githubRepository.getTrendingRepositories()

            // Then
            assertThat(result).isEqualTo(repositories)
        }
    }


}