package com.sayanx.tictactoe.presentation

sealed class GameEvent {
    data object PlayAgainButtonClick: GameEvent()
    data object RefreshButtonClick: GameEvent()
    data class TapedOnBoard(val cellNo: Int): GameEvent()
}