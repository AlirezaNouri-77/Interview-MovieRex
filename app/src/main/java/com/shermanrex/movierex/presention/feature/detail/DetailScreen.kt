package com.shermanrex.movierex.presention.feature.detail

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shermanrex.interview_movierex.R
import com.shermanrex.movierex.data.util.toReadableMessage
import com.shermanrex.movierex.domain.model.MovieUiState
import com.shermanrex.movierex.presention.component.FailurePage
import com.shermanrex.movierex.presention.component.MoviePoster
import com.shermanrex.movierex.presention.feature.detail.component.InfoDetailText
import com.shermanrex.movierex.ui.theme.InterviewMovieRexTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel<DetailViewModel>(),
    onBackClick: () -> Unit,
    movieID: String,
) {

    LaunchedEffect(movieID) {
        detailViewModel.handleActions(DetailScreenAction.GetMovieDetail(movieID))
    }

    val uiState by detailViewModel.uiState.collectAsStateWithLifecycle()

    Crossfade(
        targetState = uiState,
        modifier = modifier
            .fillMaxSize(),
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
                    FailurePage(
                        error = it.error.toReadableMessage(LocalContext.current),
                        showRetryButton = true,
                        onRetryClick = {
                            detailViewModel.handleActions(
                                DetailScreenAction.GetMovieDetail(
                                    movieID
                                )
                            )
                        }
                    )
                }

                is MovieUiState.Success -> {
                    Detail(
                        movieName = it.data.title,
                        releasedDate = it.data.released,
                        director = it.data.director,
                        actors = it.data.actors,
                        awards = it.data.awards,
                        plot = it.data.plot,
                        posterUrl = it.data.poster,
                        onBackClick = { onBackClick() },
                    )
                }

                else -> {}
            }

        }
    }

}


@Composable
private fun Detail(
    modifier: Modifier = Modifier,
    posterUrl: String,
    movieName: String,
    releasedDate: String,
    director: String,
    actors: String,
    awards: String,
    plot: String,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            MoviePoster(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp),
                cornerRadius = 0.dp,
                url = posterUrl,
            )
            IconButton(
                modifier = Modifier
                    .statusBarsPadding()
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .size(35.dp),
                onClick = {
                    onBackClick()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    contentColor = Color.White,
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = ""
                )
            }
        }
        Text(
            text = movieName,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
        Text(
            text = releasedDate,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            InfoDetailText(
                title = stringResource(R.string.director),
                description = director,
            )
            InfoDetailText(
                title = stringResource(R.string.actors),
                description = actors,
            )
            InfoDetailText(
                title = stringResource(R.string.awards),
                description = awards
            )
            InfoDetailText(
                title = stringResource(R.string.director),
                description = director
            )
            InfoDetailText(
                title = stringResource(R.string.plot),
                description = plot
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    InterviewMovieRexTheme {
        Detail(
            movieName = "The Shawshank Redemption",
            posterUrl = "",
            releasedDate = "14 Oct 1994",
            director = "Frank Darabont",
            actors = "Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler",
            awards = "Nominated for 7 Oscars. Another 19 wins &amp; 30 nominations.",
            plot = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
            onBackClick = {},
        )
    }
}
