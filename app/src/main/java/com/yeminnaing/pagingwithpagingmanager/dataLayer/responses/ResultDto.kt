package com.yeminnaing.pagingwithpagingmanager.dataLayer.responses

import com.yeminnaing.pagingwithpagingmanager.domainLayer.models.ResultModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("backdrop_path")
    val backdrop_path: String?,
    @SerialName("genre_ids")
    val genre_ids: List<Int>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("original_language")
    val original_language: String?,
    @SerialName("original_title")
    val original_title: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("poster_path")
    val poster_path: String?,
    @SerialName("release_date")
    val release_date: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("video")
    val video: Boolean?,
    @SerialName("vote_average")
    val vote_average: Double?,
    @SerialName("vote_count")
    val vote_count: Int?,
)

fun ResultDto.toDomain() = ResultModel(
      adult  = adult?:false,
  backdrop_path = backdrop_path.orEmpty(),
  genre_ids = genre_ids.orEmpty(),
  id = id?:1,
  original_language = original_language.orEmpty(),
  original_title = original_title.orEmpty(),
  overview = overview.orEmpty(),
  popularity = popularity?:0.0,
  poster_path = poster_path.orEmpty(),
  release_date = release_date.orEmpty(),
  title =title.orEmpty(),
  video =video?:false,
  vote_average =vote_average?:0.0,
  vote_count = vote_count?:0
)
