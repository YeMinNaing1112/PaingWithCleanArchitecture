package com.yeminnaing.pagingwithpagingmanager.dataLayer.responses

import com.yeminnaing.pagingwithpagingmanager.domainLayer.models.DatesModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DatesDto(
    @SerialName("maximum")
    val maximum: String?,
    @SerialName("minimum")
    val minimum: String?
)

fun DatesDto.toDomain()=DatesModel(
     minimum =maximum.orEmpty(),
      maximum = maximum.orEmpty()
)