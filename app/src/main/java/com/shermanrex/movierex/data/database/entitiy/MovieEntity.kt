package com.shermanrex.movierex.data.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "imdb_rating") val imdbRating: String,
    @ColumnInfo(name = "movie_name") val movieName: String,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "movie_id") val movieID: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "genres") val genres: String,
)