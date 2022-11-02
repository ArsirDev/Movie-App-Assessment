package com.example.movieappassessment.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class GenreResponse(

	@field:SerializedName("genres")
	val genres: List<Genres>
) {
	fun toGenreList(): List<Genres> {
		return genres.map { dataGenre ->
			Genres(
				id = dataGenre.id,
				name = dataGenre.name
			)
		}
	}
}

@Entity
data class Genres(

	@field:SerializedName("id")
	@PrimaryKey val id: Int,

	@field:SerializedName("name")
	val name: String

)
