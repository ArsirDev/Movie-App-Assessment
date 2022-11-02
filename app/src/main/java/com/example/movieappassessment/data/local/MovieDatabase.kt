package com.example.movieappassessment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieappassessment.data.local.genre.GenreDao
import com.example.movieappassessment.data.local.popular.PopularDao
import com.example.movieappassessment.data.local.upcoming.UpcomingDao
import com.example.movieappassessment.data.remote.dto.Genres
import com.example.movieappassessment.domain.model.Popular
import com.example.movieappassessment.domain.model.Upcoming
import com.example.movieappassessment.utils.Converter

@Database(entities = [Upcoming::class, Popular::class, Genres::class], version = 5)
@TypeConverters(Converter::class)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun upcomingDao(): UpcomingDao

    abstract fun popularDao(): PopularDao

    abstract fun genresDao(): GenreDao
}