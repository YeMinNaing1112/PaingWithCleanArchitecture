package com.yeminnaing.pagingwithpagingmanager.presentationLayer.di

import com.yeminnaing.pagingwithpagingmanager.dataLayer.ApiServiceKtor
import com.yeminnaing.pagingwithpagingmanager.dataLayer.repositories.GetMovieRepoKtor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
 @Provides
 @Singleton
 fun provideHttpClient():HttpClient{
     return HttpClient(){
         defaultRequest {
             url("https://api.themoviedb.org/3/movie/")
         }
         install(ContentNegotiation){
             json(
                 Json {
                     prettyPrint=true
                     isLenient=true
                 }
             )
         }
         install(Logging){
             level=LogLevel.BODY
         }
     }
 }

    @Provides
    @Singleton
    fun provideApiService(httpClient: HttpClient):ApiServiceKtor{
        return ApiServiceKtor(httpClient)
    }

   @Provides
   @Singleton
   fun provideGetMovieRepo(apiServiceKtor: ApiServiceKtor):GetMovieRepoKtor{
       return GetMovieRepoKtor(apiServiceKtor)
   }
}