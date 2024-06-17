package com.yeminnaing.pagingwithpagingmanager.domainLayer.repositories

import com.yeminnaing.pagingwithpagingmanager.domainLayer.Resources
import com.yeminnaing.pagingwithpagingmanager.domainLayer.models.NowPlayingModel

interface GetMovieRepo {
    suspend fun getMovie(page: Int): Resources<NowPlayingModel>
}