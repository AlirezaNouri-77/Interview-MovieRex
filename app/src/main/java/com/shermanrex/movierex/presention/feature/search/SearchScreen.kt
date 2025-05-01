package com.shermanrex.movierex.presention.feature.search

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shermanrex.movierex.data.util.toReadableMessage
import com.shermanrex.movierex.domain.model.MovieData
import com.shermanrex.movierex.domain.model.MovieUiState
import com.shermanrex.movierex.presention.component.FailurePage
import com.shermanrex.movierex.presention.component.MessagePage
import com.shermanrex.movierex.presention.component.MovieListItem
import com.shermanrex.movierex.presention.component.MovieTopAppBar
import com.shermanrex.movierex.presention.feature.search.component.SearchTextField
import com.shermanrex.movierex.presention.ui.theme.InterviewMovieRexTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel<SearchViewModel>(),
    navigationToDetailScreen: (movieID: String) -> Unit,
) {

    val uiState by searchViewModel.uiState.collectAsStateWithLifecycle()

    Search(
        modifier = modifier,
        uiState = uiState,
        onSearchMovie = { searchViewModel.handleAction(SearchScreenAction.GetMovie(it)) },
        navToDetail = { navigationToDetailScreen(it) },
        onRetry = { searchViewModel.handleAction(SearchScreenAction.GetMovie(it)) },
    )

}

@OptIn(FlowPreview::class)
@Composable
private fun Search(
    modifier: Modifier = Modifier,
    uiState: MovieUiState<List<MovieData>>,
    onSearchMovie: (String) -> Unit,
    navToDetail: (String) -> Unit,
    onRetry: (String) -> Unit,
) {

    var textFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(textFieldValue) {
        snapshotFlow { textFieldValue }.debounce(400L).collectLatest {
            if (it.isNotBlank() or it.isNotEmpty()) onSearchMovie(it)
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            MovieTopAppBar(
                actions = {
                    SearchTextField(
                        textFieldValue = textFieldValue,
                        onTextFieldChange = {
                            textFieldValue = it
                        },
                        onClearTextField = {
                            textFieldValue = ""
                        },
                    )
                }
            )
        },
        contentWindowInsets = WindowInsets(bottom = 0)
    ) { scaffoldPadding ->

        Crossfade(
            targetState = uiState,
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                when (it) {

                    MovieUiState.Loading -> CircularProgressIndicator()

                    is MovieUiState.Error -> FailurePage(
                        error = it.error.toReadableMessage(LocalContext.current),
                        showRetryButton = true,
                        onRetryClick = { onRetry(textFieldValue) }
                    )

                    is MovieUiState.Success -> {
                        if (it.data.isEmpty()) {
                            MessagePage(
                                modifier = Modifier.fillMaxSize(),
                                message = "Nothing Found",
                                fontSize = 18.sp,
                            )
                        } else {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                state = rememberLazyListState(),
                            ) {
                                items(
                                    items = it.data,
                                    key = { it.movieId }
                                ) {
                                    MovieListItem(
                                        item = it,
                                        onClick = {
                                            navToDetail(it)
                                        },
                                    )
                                }
                            }
                        }
                    }

                    else -> {}
                }
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    var dummyList = buildList {
        repeat(10) {
            add(
                MovieData(
                    country = "Somalia",
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
        Search(
            uiState = MovieUiState.Success(dummyList),
            onSearchMovie = {},
            navToDetail = {},
            onRetry = {},
        )
    }
}