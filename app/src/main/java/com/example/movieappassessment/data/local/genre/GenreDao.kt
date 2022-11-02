package com.example.movieappassessment.data.local.genre

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappassessment.data.remote.dto.Genres
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT * FROM genres")
    fun getAllGenres(): Flow<List<Genres>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<Genres>)

    @Query("DELETE FROM genres")
    suspend fun deleteAllGenres()
}