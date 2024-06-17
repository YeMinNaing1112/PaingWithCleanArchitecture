package com.yeminnaing.pagingwithpagingmanager.domainLayer.usecases

import com.yeminnaing.pagingwithpagingmanager.domainLayer.Resources
import com.yeminnaing.pagingwithpagingmanager.domainLayer.models.NowPlayingModel
import com.yeminnaing.pagingwithpagingmanager.domainLayer.repositories.GetMovieRepo
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val getMovieRepo: GetMovieRepo,

) {
    suspend operator fun invoke(page: Int): Resources<NowPlayingModel> {
        return getMovieRepo.getMovie(page)
    }
}