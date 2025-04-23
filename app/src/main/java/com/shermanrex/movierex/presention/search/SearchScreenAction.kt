package com.shermanrex.movierex.presention.search

sealed interface SearchScreenAction {
    data class GetMovie(val name: String) : SearchScreenAction
}