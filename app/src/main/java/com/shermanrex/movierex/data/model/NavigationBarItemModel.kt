package com.shermanrex.movierex.data.model

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
data class NavigationBarItemModel(
    val name: String,
    val icon: ImageVector,
    val route: NavigationRoutes,
)