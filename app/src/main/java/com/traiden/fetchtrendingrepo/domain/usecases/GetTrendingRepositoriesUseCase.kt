package com.traiden.fetchtrendingrepo.domain.usecases

import com.traiden.fetchtrendingrepo.domain.Repository

interface GetTrendingRepositoriesUseCase {
    suspend fun execute(): List<Repository>
}
