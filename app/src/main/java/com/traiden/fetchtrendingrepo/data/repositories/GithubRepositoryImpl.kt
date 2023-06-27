package com.traiden.fetchtrendingrepo.data.repositories

import com.traiden.fetchtrendingrepo.domain.Repository

class GithubRepositoryImpl (private val apiService: GithubApiService) : GithubRepository {
    override suspend fun getTrendingRepositories(): List<Repository> {
        // Call the API service to fetch trending repositories
        return apiService.getTrendingRepositories()
    }
}