package com.shermanrex.movierex.presention.search

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shermanrex.interview_movierex.R
import com.shermanrex.movierex.data.model.MovieData
import com.shermanrex.movierex.data.model.MovieUiState
import com.shermanrex.movierex.data.util.toReadableMessage
import com.shermanrex.movierex.presention.component.ErrorPage
import com.shermanrex.movierex.presention.component.MovieListItem
import com.shermanrex.movierex.ui.theme.InterviewMovieRexTheme
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

    SearchScreen2(
        modifier = modifier,
        onTextFieldValueChange = { searchViewModel.handleAction(SearchScreenAction.GetMovie(it)) },
        uiState = uiState,
        navToDetail = { navigationToDetailScreen(it) },
        onRetry = { searchViewModel.handleAction(SearchScreenAction.GetMovie(it)) },
    )

}

@OptIn(FlowPreview::class)
@Composable
private fun SearchScreen2(
    modifier: Modifier = Modifier,
    onTextFieldValueChange: (String) -> Unit,
    uiState: MovieUiState<List<MovieData>>,
    navToDetail: (String) -> Unit,
    onRetry: (String) -> Unit,
) {

    var textFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(textFieldValue) {
        snapshotFlow { textFieldValue }.debounce(400L).collectLatest {
            onTextFieldValueChange(it)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer),
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            textFieldValue = ""
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "",
                        )
                    }
                },
                placeholder = {
                    Text(
                        text = stringResource(
                            R.string.enter_name,
                        ),
                        fontSize = 14.sp,
                    )
                },
            )
        }


        Crossfade(
            targetState = uiState,
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
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
                            onRetryClick = { onRetry(textFieldValue) })
                    }

                    is MovieUiState.Success -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            items(
                                items = it.data,
                                key = { it.id }
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
                    country = "Somalia",
                    genres = listOf("horror", "drama"),
                    id = it,
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
        SearchScreen2(
            onTextFieldValueChange = {},
            uiState = MovieUiState.Success(dummyList),
            navToDetail = {},
            onRetry = {},
        )
    }
}