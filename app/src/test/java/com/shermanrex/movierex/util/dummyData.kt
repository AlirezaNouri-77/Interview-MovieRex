package com.shermanrex.movierex.util

import com.shermanrex.movierex.data.database.entitiy.MovieEntity
import com.shermanrex.movierex.domain.model.MovieData
import com.shermanrex.movierex.domain.model.MovieDetailModel

object DummyData {

    var movieDataDummyList = buildList {
        repeat(10) {
            add(
                MovieData(
                    country = "Usa",
                    genres = listOf("horror", "drama"),
                    movieId = it,
                    images = null,
                    imdbRating = "9.0",
                    poster = "",
                    title = "Movie Name $it",
                    year = "2000"
                )
            )
        }
    }

    val movieDetailModel = MovieDetailModel(
        actors = "actors",
        awards = "awards",
        country = "country",
        director = "director",
        genres = listOf(),
        id = 1,
        images = listOf(),
        imdbId = "imdbId",
        imdbRating = "imdbRating",
        imdbVotes = "imdbVotes",
        metascore = "metascore",
        plot = "plot",
        poster = "poster",
        rated = "rated",
        released = "released",
        runtime = "runtime",
        title = "title",
        type = "type",
        writer = "writer",
        year = "year"
    )

    val MovieEntityDummy = buildList {
        repeat(5) {
            add(
                MovieEntity(
                    id = 1,
                    imdbRating = "9.0",
                    movieName = "example Movie",
                    poster = "",
                    movieID = "1",
                    country = "Usa",
                    year = "2000",
                    genres = "drama"
                )
            )
        }
    }

}

