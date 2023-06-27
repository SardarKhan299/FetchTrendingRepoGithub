package com.traiden.fetchtrendingrepo.data.repositories

import com.traiden.fetchtrendingrepo.domain.Repository
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET
    suspend fun getTrendingRepositories(): Response<List<Repository>>
}
