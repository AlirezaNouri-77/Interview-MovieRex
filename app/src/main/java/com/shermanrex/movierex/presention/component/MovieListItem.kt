package com.shermanrex.movierex.presention.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.shermanrex.movierex.data.model.MovieData
import com.shermanrex.movierex.ui.theme.InterviewMovieRexTheme

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    item: MovieData,
    onClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(item.id.toString())
            },
    ) {
        Row(
            modifier = modifier
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
                    tint = Color.Yellow,
                    contentDescription = "",
                )
                Text(text = item.imdbRating)
            }
            Column(
                modifier = Modifier.weight(0.7f, true),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = item.title)
                item.genres?.let {
                    Text(
                        text = it.reduce { acc, string -> "$acc, $string" } ,
                        fontSize = 13.sp,
                    )
                }
            }
            AsyncImage(
                model = item.poster,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .size(80.dp)
                    .weight(0.2f),
                contentScale = ContentScale.FillWidth,
                contentDescription = "",
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
            item = MovieData(
                country = "Usa",
                genres = listOf("drama", "horror", "comedy"),
                id = 0,
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