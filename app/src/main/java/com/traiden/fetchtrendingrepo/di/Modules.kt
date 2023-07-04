package com.traiden.fetchtrendingrepo.di

import com.traiden.fetchtrendingrepo.BuildConfig.BASE_URL
import com.traiden.fetchtrendingrepo.data.repositories.ApiService
import com.traiden.fetchtrendingrepo.domain.GithubRepository
import com.traiden.fetchtrendingrepo.data.repositories.GithubRepositoryImpl
import com.traiden.fetchtrendingrepo.domain.usecases.GetTrendingRepositoriesUseCase
import com.traiden.fetchtrendingrepo.domain.usecases.GetTrendingRepositoriesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Modules {
    @Provides
    fun provideGithubApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideGithubRepository(apiService: ApiService): GithubRepository {
        return GithubRepositoryImpl(apiService)
    }

    @Provides
    fun provideGithubUsecase(githubRepository: GithubRepository): GetTrendingRepositoriesUseCase {
        return GetTrendingRepositoriesUseCaseImpl(githubRepository)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val logging =  HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val  httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }
}