package com.traiden.fetchtrendingrepo.data.repositories

import com.traiden.fetchtrendingrepo.BuildConfig.BASE_URL
import com.traiden.fetchtrendingrepo.domain.Repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubApiServiceImpl : GithubApiService {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    override suspend fun getTrendingRepositories(): List<Repository> {
        // Implement the API call using the Retrofit service and return the response
        // For example:
        val response = apiService.getTrendingRepositories()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Failed to fetch trending repositories")
        }
    }
}