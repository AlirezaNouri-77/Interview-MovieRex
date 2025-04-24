package com.shermanrex.movierex.presention.navigationGraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.shermanrex.movierex.presention.detail.DetailScreen
import com.shermanrex.movierex.presention.home.HomeScreen
import com.shermanrex.movierex.presention.search.SearchScreen

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
                navigateToDetail = { movieID ->
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