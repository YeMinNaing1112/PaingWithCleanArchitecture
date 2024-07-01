package com.yeminnaing.pagingwithpagingmanager.dataLayer.repositories

import com.yeminnaing.pagingwithpagingmanager.dataLayer.ApiServiceKtor
import com.yeminnaing.pagingwithpagingmanager.dataLayer.responses.toDomain
import com.yeminnaing.pagingwithpagingmanager.domainLayer.Resources
import com.yeminnaing.pagingwithpagingmanager.domainLayer.models.NowPlayingModel
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

class GetMovieRepoKtor(private val apiServiceKtor: ApiServiceKtor) {
    suspend fun getMovie(page:Int): com.yeminnaing.pagingwithpagingmanager.domainLayer.Resources<NowPlayingModel> {
        val apiRespond = apiServiceKtor.getMovie(page)
        return try {
               val result= apiRespond.toDomain()
            Resources.Success(result)
        }catch (e: ClientRequestException) {
            val errorMsg= e.localizedMessage ?: ""
            Resources.Error(errorMsg)
        }catch (e: ServerResponseException){
            val errorMsg=e.localizedMessage ?: ""
            Resources.Error(errorMsg)
        }catch (e:Exception){
            Resources.Error(e.localizedMessage ?: "An Unknown Error occurs")
        }
    }
}