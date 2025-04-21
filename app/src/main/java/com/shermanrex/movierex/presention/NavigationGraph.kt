package com.shermanrex.movierex.presention

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

sealed interface NavigationRoute

@Serializable
data object Home : NavigationRoute

@Serializable
data object Search : NavigationRoute

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Home,
    ) {

        composable<Home> {
            Box(Modifier.fillMaxSize().background(Color.Red))
        }

        composable<Search> {
            Box(Modifier.fillMaxSize().background(Color.Green))
        }

    }


}