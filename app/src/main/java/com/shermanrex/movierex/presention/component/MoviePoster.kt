package com.shermanrex.movierex.presention.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun MoviePoster(
    modifier: Modifier = Modifier,
    url: String,
    cornerRadius: Dp = 15.dp,
) {
    if (LocalInspectionMode.current) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(cornerRadius))
                .background(MaterialTheme.colorScheme.primary),
        )
    } else {
        AsyncImage(
            model = url,
            modifier = modifier
                .clip(RoundedCornerShape(cornerRadius)),
            contentScale = ContentScale.FillWidth,
            contentDescription = "",
        )
    }

}