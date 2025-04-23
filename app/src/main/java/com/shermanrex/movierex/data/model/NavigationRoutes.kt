package com.shermanrex.movierex.data.model

import kotlinx.serialization.Serializable

sealed interface NavigationRoutes

@Serializable
data object Home : NavigationRoutes

@Serializable
data object Search : NavigationRoutes

@Serializable
data class Detail(val movieID: String) : NavigationRoutes
