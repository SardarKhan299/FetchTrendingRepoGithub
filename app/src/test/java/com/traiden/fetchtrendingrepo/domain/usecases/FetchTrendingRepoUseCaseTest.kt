package com.traiden.fetchtrendingrepo.domain.usecases

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
            val repositories = Items(listOf(Repository("Repo 1", Owner("","OwnerName"), "Description 1", "", 4),
                Repository("Repo 2", Owner("","OwnerName2"), "Description 2", "", 5)))
            Mockito.`when`(githubRepository.getTrendingRepositories()).thenReturn(repositories)

            // When
            val result = getTrendingRepositoriesUseCase.execute()

            // Then
            assertThat(result).isEqualTo(repositories)
        }
    }
}