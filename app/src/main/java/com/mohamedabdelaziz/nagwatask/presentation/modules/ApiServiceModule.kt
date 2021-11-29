package com.mohamedabdelaziz.nagwatask.presentation.modules

import dagger.hilt.InstallIn
import javax.inject.Singleton
import com.mohamedabdelaziz.nagwatask.data.datasource.remote.ApiService
import com.mohamedabdelaziz.nagwatask.domain.repositories.ContentRepositoryRemote
import com.mohamedabdelaziz.nagwatask.data.repositoryimp.ContentRepositoryRemoteImp
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Singleton
    @Provides
    fun provideTrendingRepositoryRemote(apiService: ApiService): ContentRepositoryRemote =
        ContentRepositoryRemoteImp(apiService)

}