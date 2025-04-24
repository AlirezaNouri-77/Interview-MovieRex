package com.shermanrex.movierex.presention.feature.home

sealed interface HomeScreenAction {
    data object GetMovies : HomeScreenAction
}