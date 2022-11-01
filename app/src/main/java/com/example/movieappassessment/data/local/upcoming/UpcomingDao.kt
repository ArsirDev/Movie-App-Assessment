package com.example.movieappassessment.data.local.upcoming

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappassessment.domain.model.Upcoming
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingDao {
    @Query("SELECT * FROM upcoming")
    fun getAllUpcoming(): Flow<List<Upcoming>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcoming(popular: List<Upcoming>)

    @Query("DELETE FROM upcoming")
    suspend fun deleteAllUpcoming()
}