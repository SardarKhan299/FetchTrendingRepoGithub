package com.traiden.fetchtrendingrepo.data.repositories

import com.traiden.fetchtrendingrepo.domain.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.internal.stubbing.answers.ThrowsException
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

            `when`(apiService.getTrendingRepositories("")).thenReturn(repositories)

            // When
            val result = githubRepository.getTrendingRepositories()

            // Then
            assertThat(result).isEqualTo(repositories)
        }
    }


    @Test
    fun `fetchTrendingRepositories_error_case`() {
        runBlocking {

            // Given
            `when`(apiService.getTrendingRepositories("")).thenReturn(null)

            // When
            val result = githubRepository.getTrendingRepositories()

            // Then
            assertThat(result).isEqualTo(null)
        }
    }


    @Test
    fun `fetchTrendingRepositories Return Empty List`() {
        runBlocking {

            // Given
            `when`(apiService.getTrendingRepositories("")).thenReturn(Items(emptyList()))

            // When
            val result = githubRepository.getTrendingRepositories()

            // Then
            assertThat(result).isEqualTo(Items(emptyList()))
        }
    }


}