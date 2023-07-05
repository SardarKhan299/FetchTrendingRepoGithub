package com.traiden.fetchtrendingrepo.data.repositories

import com.traiden.fetchtrendingrepo.domain.Items
import com.traiden.fetchtrendingrepo.domain.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiService {
    @GET("repositories?")
    suspend fun getTrendingRepositories(@Query("q") query:String): Items
}
