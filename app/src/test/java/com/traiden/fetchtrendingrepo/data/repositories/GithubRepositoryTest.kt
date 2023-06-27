package com.traiden.fetchtrendingrepo.data.repositories

import com.traiden.fetchtrendingrepo.domain.Repository
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GithubRepositoryTest {


    @Mock
    private lateinit var apiService : GithubApiService
    private lateinit var githubRepository: GithubRepository

    @Before
    fun setup(){
        githubRepository = GithubRepositoryImpl(apiService)
    }

    @Test
    fun fetchTrendingRepositories_should_return_list_of_repositories() = runBlocking {
        // Given
        val repositories = listOf(
            Repository("Repo 1", "Owner 1", "Description 1","","java",4),
            Repository("Repo 2", "Owner 2", "Description 2","","kotlin",3)
        )
        Mockito.`when`(apiService.getTrendingRepositories()).thenReturn(repositories)

        // When
        val result = githubRepository.getTrendingRepositories()

        // Then
        assertThat(result).isEqualTo(repositories)

    }


}