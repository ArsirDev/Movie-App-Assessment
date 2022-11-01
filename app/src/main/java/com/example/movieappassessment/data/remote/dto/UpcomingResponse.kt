package com.example.movieappassessment.data.remote.dto

import com.example.movieappassessment.domain.model.Upcoming
import com.google.gson.annotations.SerializedName

data class UpcomingResponse(

    @field:SerializedName("dates")
    val dates: Dates,

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<Upcoming>,

    @field:SerializedName("total_results")
    val totalResults: Int
) {
    fun toUpcomingList(): List<Upcoming> {
        return results.map { upcoming ->
            Upcoming(
                overview = upcoming.overview,
                originalLanguage = upcoming.originalLanguage,
                originalTitle = upcoming.originalTitle,
                video = upcoming.video,
                title = upcoming.title,
                genreIds = upcoming.genreIds,
                posterPath = upcoming.posterPath,
                backdropPath = upcoming.backdropPath,
                releaseDate = upcoming.releaseDate,
                popularity = upcoming.popularity,
                voteAverage = upcoming.voteAverage,
                id = upcoming.id,
                adult = upcoming.adult,
                voteCount = upcoming.voteCount,
            )
        }
    }
}

data class Dates(

    @field:SerializedName("maximum")
    val maximum: String,

    @field:SerializedName("minimum")
    val minimum: String
)
