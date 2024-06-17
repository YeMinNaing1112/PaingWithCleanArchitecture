package com.yeminnaing.pagingwithpagingmanager.presentationLayer.di

import com.yeminnaing.pagingwithpagingmanager.dataLayer.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideJson() = Json { ignoreUnknownKeys = true }


    @Provides
    @Singleton
    fun provideRetrofit(
        json: Json,
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()
                )
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit,
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}