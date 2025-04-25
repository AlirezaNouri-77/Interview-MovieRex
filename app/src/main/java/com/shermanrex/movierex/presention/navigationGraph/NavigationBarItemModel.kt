package com.shermanrex.movierex.presention.navigationGraph

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItemModel(
    val name: String,
    val icon: ImageVector,
    val route: NavigationRoutes,
)