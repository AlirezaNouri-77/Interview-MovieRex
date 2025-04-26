package com.shermanrex.movierex.domain.model

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import com.google.gson.annotations.SerializedName

@Keep
@Stable
data class MovieModel(
    @SerializedName("data") val movie: List<MovieData>,
)

@Keep
data class MovieData(
    @SerializedName("country") val country: String,
    @SerializedName("genres") val genres: List<String>?,
    @SerializedName("id") val movieId: Int,
    @SerializedName("images") val images: List<String>?,
    @SerializedName("imdb_rating") val imdbRating: String?,
    @SerializedName("poster") val poster: String,
    @SerializedName("title") val title: String,
    @SerializedName("year") val year: String,
)


