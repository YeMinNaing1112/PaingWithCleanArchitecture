package com.yeminnaing.pagingwithpagingmanager.dataLayer.responses

import com.yeminnaing.pagingwithpagingmanager.domainLayer.models.NowPlayingModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingResponse(
    @SerialName("dates")
    val dates: DatesDto?,

    @SerialName("page")
    val page: Int?,

    @SerialName("results")
    val results: List<ResultDto>?,

    @SerialName("total_pages")
    val total_pages: Int?,

    @SerialName("total_results")
    val total_results: Int?
)

fun NowPlayingResponse.toDomain()=NowPlayingModel(
    datesModel = dates!!.toDomain(),
    page=page?:1,
    resultModels = results?.map { it.toDomain() }.orEmpty(),
    total_pages = total_results?:1,
    total_results = total_results?:1

)