package com.sayanx.tictactoe.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sayanx.tictactoe.presentation.GameState
import com.sayanx.tictactoe.presentation.VictoryType

@Composable
fun DrawVictoryLine(
    gameState: GameState,
    screenWidth: Int
) {
    when (gameState.victoryType) {
        VictoryType.FirstWinHorizontalLine -> FirstWinHorizontalLine(screenWidth = screenWidth.dp)
        VictoryType.SecondWinHorizontalLine -> SecondWinHorizontalLine(screenWidth = screenWidth.dp)
        VictoryType.ThirdWinHorizontalLine -> ThirdWinHorizontalLine(screenWidth = screenWidth.dp)
        VictoryType.FirstWinVerticalLine -> FirstWinVerticalLine(screenWidth = screenWidth.dp)
        VictoryType.SecondWinVerticalLine -> SecondWinVerticalLine(screenWidth = screenWidth.dp)
        VictoryType.ThirdWinVerticalLine -> ThirdWinVerticalLine(screenWidth = screenWidth.dp)
        VictoryType.FirstWinDiagonalLine -> FirstWinDiagonalLine(screenWidth = screenWidth.dp)
        VictoryType.SecondWinDiagonalLine -> SecondWinDiagonalLine(screenWidth = screenWidth.dp)
        else -> Unit
    }
}