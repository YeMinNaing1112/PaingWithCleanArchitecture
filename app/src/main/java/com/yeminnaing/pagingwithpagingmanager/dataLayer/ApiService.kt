package com.yeminnaing.pagingwithpagingmanager.dataLayer

import com.yeminnaing.pagingwithpagingmanager.dataLayer.responses.NowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey:String = APIKEY,
        @Query("language") language:String= LANGUAGE,
        @Query("page") page:Int
    ):NowPlayingResponse
}