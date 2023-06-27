package com.traiden.fetchtrendingrepo.data.repositories

import com.traiden.fetchtrendingrepo.domain.Repository

interface GithubRepository {
    suspend fun getTrendingRepositories(): List<Repository>
}