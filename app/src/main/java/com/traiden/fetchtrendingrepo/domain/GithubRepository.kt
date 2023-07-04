package com.traiden.fetchtrendingrepo.domain

import com.traiden.fetchtrendingrepo.domain.Repository

interface GithubRepository {
    suspend fun getTrendingRepositories(): Items
}