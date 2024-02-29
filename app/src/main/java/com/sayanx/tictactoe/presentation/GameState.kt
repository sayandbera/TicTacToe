package com.sayanx.tictactoe.presentation

data class GameState(
    val playerCircleWinCount: Int = 0,
    val playerCrossWinCount: Int = 0,
    val currentTurn: BoardCellValue = BoardCellValue.CIRCLE,
    val victoryType: VictoryType? = VictoryType.None,
    val gameStateHint: GameStateHint = GameStateHint.RUNNING
)

enum class BoardCellValue  {
    CIRCLE,
    CROSS,
    NONE
}

enum class VictoryType {
    None,
    FirstWinHorizontalLine,
    SecondWinHorizontalLine,
    ThirdWinHorizontalLine,
    FirstWinVerticalLine,
    SecondWinVerticalLine,
    ThirdWinVerticalLine,
    FirstWinDiagonalLine,
    SecondWinDiagonalLine
}

enum class GameStateHint {
    RUNNING,
    WIN,
    DRAW
}
