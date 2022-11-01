package com.example.movieappassessment.data.local.popular

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappassessment.domain.model.Popular
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularDao {
    @Query("SELECT * FROM popular")
    fun getAllPopular(): Flow<List<Popular>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopular(popular: List<Popular>)

    @Query("DELETE FROM popular")
    suspend fun deleteAllPopular()
}