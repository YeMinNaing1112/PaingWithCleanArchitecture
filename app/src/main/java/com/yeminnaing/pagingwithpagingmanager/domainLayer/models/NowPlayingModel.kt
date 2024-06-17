package com.yeminnaing.pagingwithpagingmanager.domainLayer.models

data class NowPlayingModel(
    val datesModel: DatesModel,
    val page: Int,
    val resultModels: List<ResultModel>,
    val total_pages: Int,
    val total_results: Int
)