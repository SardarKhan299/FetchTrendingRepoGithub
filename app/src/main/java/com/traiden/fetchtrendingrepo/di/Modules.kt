package com.traiden.fetchtrendingrepo.di

import com.traiden.fetchtrendingrepo.data.repositories.GithubApiService
import com.traiden.fetchtrendingrepo.data.repositories.GithubRepository
import com.traiden.fetchtrendingrepo.data.repositories.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class Modules {
    @Provides
    fun provideGithubApiService(retrofit: Retrofit): GithubApiService {
        return retrofit.create(GithubApiService::class.java)
    }

    @Provides
    fun provideGithubRepository(apiService: GithubApiService): GithubRepository {
        return GithubRepositoryImpl(apiService)
    }
}