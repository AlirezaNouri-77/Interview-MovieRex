package com.shermanrex.movierex.presention

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shermanrex.movierex.presention.feature.MainScreen
import com.shermanrex.movierex.presention.ui.theme.InterviewMovieRexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewMovieRexTheme {
                MainScreen()
            }
        }
    }
}