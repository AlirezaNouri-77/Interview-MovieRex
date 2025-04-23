package com.shermanrex.movierex.presention.home

sealed interface HomeScreenAction {
    data object GetMovies : HomeScreenAction
}