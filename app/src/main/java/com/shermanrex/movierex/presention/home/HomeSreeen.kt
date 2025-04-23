package com.shermanrex.movierex.presention.home

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shermanrex.interview_movierex.R
import com.shermanrex.movierex.data.model.MovieUiState
import com.shermanrex.movierex.data.util.toReadableMessage
import com.shermanrex.movierex.presention.component.ErrorPage
import com.shermanrex.movierex.presention.component.MovieListItem
import com.shermanrex.movierex.presention.component.MovieTopAppBar
import com.shermanrex.movierex.ui.theme.InterviewMovieRexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navToDetail: (movieID: String) -> Unit,
) {

    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MovieTopAppBar(
                title = stringResource(R.string.Home),
            )
        },
    ) { scaffoldPadding ->

        Crossfade(
            targetState = uiState,
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                when (it) {

                    MovieUiState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is MovieUiState.Error -> {
                        ErrorPage(
                            error = it.error.toReadableMessage(LocalContext.current),
                            showRetryButton = true,
                            onRetryClick = {
                                homeViewModel.handleAction(HomeScreenAction.GetMovies)
                            }
                        )
                    }

                    is MovieUiState.Success -> {
                        LazyColumn {
                            items(it.data) {
                                MovieListItem(
                                    item = it,
                                    onClick = {
                                        navToDetail(it)
                                    },
                                )
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    InterviewMovieRexTheme {
        HomeScreen(
            navToDetail = {},
        )
    }
}