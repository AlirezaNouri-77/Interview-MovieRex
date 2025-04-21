package com.shermanrex.movierex.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import com.shermanrex.movierex.presention.NavigationRoute

@Immutable
data class NavigationBarItemModel(
    val name: String,
    val icon: ImageVector,
    val route: NavigationRoute,
)