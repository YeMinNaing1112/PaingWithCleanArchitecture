package com.yeminnaing.pagingwithpagingmanager.dataLayer

import com.yeminnaing.pagingwithpagingmanager.dataLayer.responses.NowPlayingResponse
import com.yeminnaing.pagingwithpagingmanager.dataLayer.responses.ResultDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiServiceKtor(private val httpClient: HttpClient) {
    suspend fun getMovie(page: Int)=httpClient.get("now_playing"){
        parameter("api_key", APIKEY)
        parameter("language", LANGUAGE)
        parameter("page", page)
    }.body<NowPlayingResponse>()
}