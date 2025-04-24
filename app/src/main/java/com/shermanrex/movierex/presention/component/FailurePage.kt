package com.shermanrex.movierex.presention.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shermanrex.interview_movierex.R
import com.shermanrex.movierex.ui.theme.InterviewMovieRexTheme

@Composable
fun FailurePage(
    modifier: Modifier = Modifier,
    error: String,
    showRetryButton: Boolean = false,
    onRetryClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = error,
            fontWeight = FontWeight.SemiBold,
            fontSize = 19.sp,
        )
        if (showRetryButton) {
            TextButton(
                onClick = {
                    onRetryClick()
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            ) {
                Text(
                    text = stringResource(R.string.retry),
                    fontSize = 15.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    InterviewMovieRexTheme {
        FailurePage(
            error = "very very very very very very bad error",
            showRetryButton = true,
        )
    }
}