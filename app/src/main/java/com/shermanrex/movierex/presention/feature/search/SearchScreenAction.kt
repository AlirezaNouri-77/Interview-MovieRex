package com.shermanrex.movierex.presention.feature.search

sealed interface SearchScreenAction {
    data class GetMovie(val name: String) : SearchScreenAction
}