package com.shermanrex.movierex.presention.detail

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.shermanrex.interview_movierex.R
import com.shermanrex.movierex.data.model.MovieUiState
import com.shermanrex.movierex.data.util.toReadableMessage
import com.shermanrex.movierex.presention.component.ErrorPage
import com.shermanrex.movierex.presention.component.MovieTopAppBar
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

    Scaffold(
        modifier = modifier,
        topBar = {
            MovieTopAppBar(
                navigationIcon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                onNavigationIconClick = { onBackClick() }
            )
        }
    ) { scaffoldPadding ->
        Crossfade(
            targetState = uiState,
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
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
                        )
                    }

                    else -> {}
                }
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
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = posterUrl,
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .size(200.dp),
            contentScale = ContentScale.FillWidth,
            contentDescription = "",
        )
        Text(
            text = movieName,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
        Text(
            text = releasedDate,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        ) {
            Text(
                text = "${stringResource(R.string.director)}: $director",
            )
            Text(
                text = "${stringResource(R.string.actors)}: $actors",
            )
            Text(
                text = "${stringResource(R.string.awards)}: $awards",
            )
            Text(text = plot)
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
            plot = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
        )
    }
}