package com.shermanrex.movierex.presention.detail.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.shermanrex.movierex.ui.theme.InterviewMovieRexTheme

@Composable
fun InfoDetailText(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    var text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        ) {
            append(title)
            append("\n")
            withStyle(
                style = SpanStyle(
                    fontSize = 14.sp,
                )
            ) {
                append(description)
            }
        }
    }
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        textAlign = TextAlign.Start,
    )
}


@Preview()
@Composable
private fun PreviewInfoDetailText() {
    InterviewMovieRexTheme {
        InfoDetailText(
            title = "director",
            description = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
        )
    }
}