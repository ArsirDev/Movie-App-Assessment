package com.example.movieappassessment.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GenreResponse(

	@field:SerializedName("genres")
	val genres: List<Genres>
)

data class Genres(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String

)
