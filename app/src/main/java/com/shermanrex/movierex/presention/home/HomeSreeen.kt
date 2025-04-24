package com.shermanrex.movierex.presention.home

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
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
import com.shermanrex.movierex.domain.model.MovieData
import com.shermanrex.movierex.domain.model.MovieUiState
import com.shermanrex.movierex.data.util.toReadableMessage
import com.shermanrex.movierex.presention.component.FailurePage
import com.shermanrex.movierex.presention.component.MovieListItem
import com.shermanrex.movierex.presention.component.MovieTopAppBar
import com.shermanrex.movierex.ui.theme.InterviewMovieRexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (movieID: String) -> Unit,
) {

    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Home(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onRetry = { homeViewModel.handleActions(HomeScreenAction.GetMovies) },
        navigateToDetail = { navigateToDetail(it) }
    )

}

@Composable
private fun Home(
    modifier: Modifier = Modifier,
    uiState: MovieUiState<List<MovieData>>,
    onRetry: () -> Unit,
    navigateToDetail: (movieID: String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MovieTopAppBar(
                title = stringResource(R.string.Home),
            )
        },
        contentWindowInsets = WindowInsets(bottom = 0),
    ) { scaffoldPadding ->

        Crossfade(
            targetState = uiState,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding),
                contentAlignment = Alignment.Center,
            ) {
                when (it) {

                    MovieUiState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is MovieUiState.Error -> {
                        FailurePage(
                            error = it.error.toReadableMessage(LocalContext.current),
                            showRetryButton = true,
                            onRetryClick = {
                                onRetry()
                            }
                        )
                    }

                    is MovieUiState.Success -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            items(it.data) {
                                MovieListItem(
                                    item = it,
                                    onClick = {
                                        navigateToDetail(it)
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
    var dummyList = buildList {
        repeat(10) {
            add(
                MovieData(
                    country = "Usa",
                    genres = listOf("horror", "drama"),
                    movieId = it,
                    images = null,
                    imdbRating = "9.0",
                    poster = "",
                    title = "Movie Name $it",
                    year = "2000"
                )
            )
        }
    }
    InterviewMovieRexTheme {
        Home(
            uiState = MovieUiState.Success(dummyList),
            onRetry = {},
            navigateToDetail = {},
        )
    }
}