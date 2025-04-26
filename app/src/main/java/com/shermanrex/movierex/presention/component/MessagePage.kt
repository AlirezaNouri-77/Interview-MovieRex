package com.shermanrex.movierex.presention.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.shermanrex.movierex.ui.theme.InterviewMovieRexTheme

@Composable
fun MessagePage(
    modifier: Modifier = Modifier,
    message: String,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Bold,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = message,
            fontWeight = fontWeight,
            fontSize = fontSize,
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    InterviewMovieRexTheme {
        MessagePage(
            message = "Nothing Found",
            fontSize = 18.sp,
        )
    }
}