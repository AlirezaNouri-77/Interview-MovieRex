package com.shermanrex.movierex.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.shermanrex.movierex.presention.navigationGraph.NavigationRoutes

data class NavigationBarItemModel(
    val name: String,
    val icon: ImageVector,
    val route: NavigationRoutes,
)