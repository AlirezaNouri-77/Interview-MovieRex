package com.shermanrex.movierex.presention

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.shermanrex.movierex.data.model.Detail
import com.shermanrex.movierex.data.model.Home
import com.shermanrex.movierex.data.model.Search
import com.shermanrex.movierex.presention.detail.DetailScreen
import com.shermanrex.movierex.presention.home.HomeScreen
import com.shermanrex.movierex.presention.search.SearchScreen
import kotlinx.serialization.Serializable

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
            HomeScreen(
                navToDetail = { movieID ->
                    navHostController.navigate(Detail(movieID))
                },
            )
        }

        composable<Search> {
            SearchScreen(
                navigationToDetailScreen = { movieID ->
                    navHostController.navigate(Detail(movieID))
                },
            )
        }

        composable<Detail> { navBackStackEntry ->
            val movieID = navBackStackEntry.toRoute<Detail>().movieID
            DetailScreen(
                movieID = movieID,
                onBackClick = {
                    navHostController.popBackStack()
                },
            )
        }

    }


}