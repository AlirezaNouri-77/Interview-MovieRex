package com.shermanrex.movierex.data.util

import com.shermanrex.movierex.data.database.entitiy.MovieEntity
import com.shermanrex.movierex.domain.model.MovieData

fun MovieData.toMovieEntity(): MovieEntity {
    return MovieEntity(
        imdbRating = this.imdbRating ?: "",
        movieName = this.title,
        poster = this.poster,
        movieID = this.movieId.toString(),
        country = this.country,
        year = this.year,
        genres = this.genres?.joinToString() ?: "",
    )
}

fun MovieEntity.toMovieData(): MovieData {
    return MovieData(
        country = this.country,
        movieId = this.movieID.toInt(),
        images = null,
        imdbRating = this.imdbRating,
        poster = this.poster,
        title = this.movieName,
        year = this.year,
        genres = this.genres.toListOfString(),
    )
}