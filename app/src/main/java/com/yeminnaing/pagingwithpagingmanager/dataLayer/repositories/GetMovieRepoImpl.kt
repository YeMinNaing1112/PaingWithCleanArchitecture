package com.yeminnaing.pagingwithpagingmanager.dataLayer.repositories

import android.util.Log
import com.yeminnaing.pagingwithpagingmanager.dataLayer.ApiService
import com.yeminnaing.pagingwithpagingmanager.dataLayer.responses.toDomain
import com.yeminnaing.pagingwithpagingmanager.domainLayer.Resources
import com.yeminnaing.pagingwithpagingmanager.domainLayer.models.NowPlayingModel
import com.yeminnaing.pagingwithpagingmanager.domainLayer.repositories.GetMovieRepo
import javax.inject.Inject

class GetMovieRepoImpl  @Inject constructor(
    private val apiService: ApiService
) :GetMovieRepo {
    override suspend fun getMovie(page: Int): Resources<NowPlayingModel> {
        return try {
            val response = apiService.getNowPlayingMovie(page = page).toDomain()
            Resources.Success(response)
        } catch (e: Exception) {
            Resources.Error(e.localizedMessage ?: "Unknown Error")

        }
    }
}