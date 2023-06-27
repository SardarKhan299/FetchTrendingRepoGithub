package com.traiden.fetchtrendingrepo.data.repositories

import org.junit.Before

class GithubRepositoryTest {


    private lateinit var apiService : GithubApiService
    private lateinit var githubRepository: GithubRepository

    @Before
    fun setup(){
        githubRepository = GithubRepositoryImpl(apiService)
    }


}