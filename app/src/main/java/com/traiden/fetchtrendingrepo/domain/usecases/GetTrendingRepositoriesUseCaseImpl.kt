package com.traiden.fetchtrendingrepo.domain.usecases

import com.traiden.fetchtrendingrepo.domain.GithubRepository
import com.traiden.fetchtrendingrepo.domain.Items
import com.traiden.fetchtrendingrepo.domain.Repository

class GetTrendingRepositoriesUseCaseImpl(private val githubRepository: GithubRepository) : GetTrendingRepositoriesUseCase {
    override suspend fun execute(): Items {
        return githubRepository.getTrendingRepositories()
    }
}
