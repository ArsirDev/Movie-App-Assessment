package com.example.movieappassessment.data.remote.dto

import com.example.movieappassessment.domain.model.Popular
import com.google.gson.annotations.SerializedName

data class PopularResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<Popular>,

    @field:SerializedName("total_results")
    val totalResults: Int
) {
    fun toPopularList(): List<Popular> {
        return results.map { popular ->
            Popular(
                id = popular.id,
                overview = popular.overview,
                originalLanguage = popular.originalLanguage,
                originalTitle = popular.originalTitle,
                video = popular.video,
                title = popular.title,
                genreIds = popular.genreIds,
                posterPath = popular.posterPath,
                backdropPath = popular.backdropPath,
                releaseDate = popular.releaseDate,
                popularity = popular.popularity,
                voteAverage = popular.voteAverage,
                adult = popular.adult,
                voteCount = popular.voteCount,
            )
        }
    }
}

