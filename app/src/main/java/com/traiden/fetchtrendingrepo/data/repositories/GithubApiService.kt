package com.traiden.fetchtrendingrepo.data.repositories

import com.traiden.fetchtrendingrepo.domain.Repository

interface GithubApiService {
    suspend fun getTrendingRepositories(): List<Repository>
}
