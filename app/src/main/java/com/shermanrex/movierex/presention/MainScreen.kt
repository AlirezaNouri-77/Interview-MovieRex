package com.shermanrex.movierex.presention

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.shermanrex.movierex.ui.theme.InterviewMovieRexTheme

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    var navHostController = rememberNavController()
    var currentDestinationId = navHostController.currentDestination?.id ?: 0

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            MovieRexBottomBar(
                onClick = {
                    navHostController.navigate(it) {
                        popUpTo(currentDestinationId) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    ) { scaffoldPadding ->
        Box(modifier = Modifier.padding(scaffoldPadding)) {
            NavigationGraph(
                navHostController = navHostController,
            )
        }
    }

}

@Preview
@Composable
private fun PreviewMainScreen() {
    InterviewMovieRexTheme {
        MainScreen()
    }
}