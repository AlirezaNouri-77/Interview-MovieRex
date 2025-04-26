package com.shermanrex.movierex.presention.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.shermanrex.interview_movierex.R
import com.shermanrex.movierex.presention.navigationGraph.NavigationBarItemModel
import com.shermanrex.movierex.presention.navigationGraph.Home
import com.shermanrex.movierex.presention.navigationGraph.NavigationRoutes
import com.shermanrex.movierex.presention.navigationGraph.Search
import com.shermanrex.movierex.presention.ui.theme.InterviewMovieRexTheme

@Composable
fun MovieRexBottomBar(
    modifier: Modifier = Modifier,
    onClick: (NavigationRoutes, Int) -> Unit,
    currentNavigationIndex: Int,
) {

    val navigationItems = arrayOf(
        NavigationBarItemModel(
            name = stringResource(id = R.string.Home),
            icon = Icons.Default.Home,
            route = Home
        ),
        NavigationBarItemModel(
            name = stringResource(id = R.string.Search),
            icon = Icons.Default.Search,
            route = Search
        ),
    )

    NavigationBar(
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        navigationItems.onEachIndexed { index, item ->
            NavigationBarItem(
                onClick = {
                    onClick(item.route, index)
                },
                selected = currentNavigationIndex == index,
                label = {
                    Text(text = item.name)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    selectedIconColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
                    unselectedIconColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                ),
            )
        }
    }

}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun Preview() {
    InterviewMovieRexTheme {
        MovieRexBottomBar(
            currentNavigationIndex = 0,
            onClick = { _, _ -> },
        )
    }
}

