package com.mohamedabdelaziz.nagwatask.core.di

import com.mohamedabdelaziz.nagwatask.core.utils.constants.Constants
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val baseUrl: String
        get() = Constants.BASE_URL

    @Provides
    @Singleton
    fun providesOkHttpClientBuilder(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build()


    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}