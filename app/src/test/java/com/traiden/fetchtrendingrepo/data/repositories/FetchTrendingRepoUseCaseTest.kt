package com.traiden.fetchtrendingrepo.data.repositories

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
class FetchTrendingRepoUseCaseTest {
    @Mock
    private lateinit var githubRepository: GithubRepository

    private lateinit var getTrendingRepositoriesUseCase: GetTrendingRepositoriesUseCase

    @Before
    fun setup() {
        getTrendingRepositoriesUseCase = GetTrendingRepositoriesUseCaseImpl(githubRepository)
    }

    @Test
    fun `execute should return list of repositories`() {
        runBlocking {
            // Given
            val repositories = listOf(
                Repository("Repo 1", "Owner 1", "Description 1", "", "java", 4),
                Repository("Repo 2", "Owner 2", "Description 2", "", "kotlin", 3)
            )
            Mockito.`when`(githubRepository.getTrendingRepositories()).thenReturn(repositories)

            // When
            val result = getTrendingRepositoriesUseCase.execute()

            // Then
            assertThat(result).isEqualTo(repositories)
        }
    }
}