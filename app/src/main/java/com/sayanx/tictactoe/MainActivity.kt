package com.sayanx.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sayanx.tictactoe.presentation.GameScreen
import com.sayanx.tictactoe.presentation.GameViewModel
import com.sayanx.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TicTacToeTheme {
                val viewModel = viewModel<GameViewModel>()
                GameScreen(viewModel)
            }
        }
    }
}