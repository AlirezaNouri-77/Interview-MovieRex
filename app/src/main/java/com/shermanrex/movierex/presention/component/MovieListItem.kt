package com.shermanrex.movierex.presention.component

import android.content.res.Configuration
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shermanrex.interview_movierex.R
import com.shermanrex.movierex.domain.model.MovieData
import com.shermanrex.movierex.ui.theme.InterviewMovieRexTheme

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    item: MovieData,
    onClick: (String) -> Unit,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick(item.movieId.toString())
                },
    ) {
        Row(
            modifier =
                modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(0.1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    tint = colorResource(R.color.yellow),
                    contentDescription = "",
                )
                Text(text = item.imdbRating)
            }
            Spacer(modifier.width(5.dp))
            Column(
                modifier = Modifier.weight(0.7f, true),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.basicMarquee(),
                    text = item.title,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1
                )
                item.genres?.let {
                    Text(
                        text = it.joinToString(),
                        fontSize = 14.sp,
                    )
                }
                Text(
                    text = "${item.country} ${item.year}",
                    fontSize = 13.sp,
                )
            }
            Spacer(modifier.width(5.dp))
            MoviePoster(
                modifier = Modifier
                    .size(90.dp)
                    .weight(0.2f),
                url = item.poster,
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    InterviewMovieRexTheme {
        MovieListItem(
            item =
                MovieData(
                    country = "Usa",
                    genres = listOf("drama", "horror", "comedy"),
                    movieId = 0,
                    images = listOf(),
                    imdbRating = "9.0",
                    poster = "",
                    title = "The Shawshank Redemption",
                    year = "1994",
                ),
            onClick = {},
        )
    }
}
