package com.traiden.fetchtrendingrepo.data.repositories

import com.traiden.fetchtrendingrepo.domain.GithubRepository
import com.traiden.fetchtrendingrepo.domain.Items
import com.traiden.fetchtrendingrepo.domain.Repository
import retrofit2.Response

class GithubRepositoryImpl (private val apiService: ApiService) : GithubRepository {
    override suspend fun getTrendingRepositories(): Items {
        // Call the API service to fetch trending repositories
        return apiService.getTrendingRepositories("language=+sort:stars")
    }
}