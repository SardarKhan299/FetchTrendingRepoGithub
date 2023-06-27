package com.traiden.fetchtrendingrepo.domain.usecases

import com.traiden.fetchtrendingrepo.data.repositories.GithubRepository
import com.traiden.fetchtrendingrepo.domain.Repository

class GetTrendingRepositoriesUseCaseImpl(private val githubRepository: GithubRepository) : GetTrendingRepositoriesUseCase {
    override suspend fun execute(): List<Repository> {
        return githubRepository.getTrendingRepositories()
    }
}
