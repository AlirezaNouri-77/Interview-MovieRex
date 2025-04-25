package com.shermanrex.movierex.presention

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shermanrex.movierex.presention.component.MovieRexBottomBar
import com.shermanrex.movierex.presention.navigationGraph.Detail
import com.shermanrex.movierex.presention.navigationGraph.Home
import com.shermanrex.movierex.presention.navigationGraph.NavigationGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    var navHostController = rememberNavController()
    var currentDestinationId = navHostController.currentDestination?.id ?: 0
    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()

    val shouldHideBottomBar = remember(currentBackStackEntry) {
        (navHostController.currentBackStackEntry?.destination?.hasRoute(Detail::class)
            ?: Home) == false
    }

    var currentNavigationBarIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = shouldHideBottomBar,
                exit = slideOutHorizontally { it },
                enter = slideInVertically { it + it / 2 }
            ) {
                MovieRexBottomBar(
                    onClick = { route, index ->
                        currentNavigationBarIndex = index
                        navHostController.navigate(route) {
                            popUpTo(currentDestinationId) {
                                inclusive = true
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    currentNavigationIndex = currentNavigationBarIndex,
                )
            }
        },
        contentWindowInsets = WindowInsets(top = 0)
    ) { scaffoldPadding ->
        Box(modifier = Modifier.padding(scaffoldPadding)) {
            NavigationGraph(
                navHostController = navHostController,
            )
        }
    }

}
