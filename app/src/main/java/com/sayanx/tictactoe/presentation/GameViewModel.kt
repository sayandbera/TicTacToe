package com.sayanx.tictactoe.presentation

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel: ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    val boardItems: MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE
    )

    fun onUserAction(
        action: GameEvent,
        context: Context
    ) {
        when(action) {
            GameEvent.PlayAgainButtonClick -> {
                boardItems.forEach { (key, _) ->
                    boardItems[key] = BoardCellValue.NONE
                }
                _state.value = state.value.copy(
                    currentTurn = BoardCellValue.CIRCLE,
                    victoryType = VictoryType.None,
                    gameStateHint = GameStateHint.RUNNING
                )
            }
            GameEvent.RefreshButtonClick -> {
                boardItems.forEach { (key, _) ->
                    boardItems[key] = BoardCellValue.NONE
                }
                _state.value = state.value.copy(
                    playerCircleWinCount = 0,
                    playerCrossWinCount = 0,
                    currentTurn = BoardCellValue.CIRCLE,
                    victoryType = VictoryType.None,
                    gameStateHint = GameStateHint.RUNNING
                )
            }
            is GameEvent.TapedOnBoard -> {
                if (state.value.gameStateHint == GameStateHint.RUNNING) {
                    addValueToBoard(
                        cellNo = action.cellNo,
                        context = context
                    )
                }
            }
        }
    }

    private fun addValueToBoard(
        cellNo: Int,
        context: Context
    ) {
        if (boardItems[cellNo] != BoardCellValue.NONE) {
            return
        }
        if (_state.value.currentTurn == BoardCellValue.CIRCLE) {
            boardItems[cellNo] = BoardCellValue.CIRCLE
            if (checkForVictory(boardCellValue = BoardCellValue.CIRCLE)) {
                _state.value = _state.value.copy(
                    gameStateHint = GameStateHint.WIN,
                    currentTurn = BoardCellValue.NONE,
                    playerCircleWinCount = _state.value.playerCircleWinCount + 1
                )
            } else if (gameHasDrawn()) {
                _state.value = _state.value.copy(
                    gameStateHint = GameStateHint.DRAW,
                    currentTurn = BoardCellValue.NONE
                )
                showToastMessage(context,"It's a draw! ")
            }
            _state.value = _state.value.copy(
                currentTurn = BoardCellValue.CROSS
            )
        } else if (_state.value.currentTurn == BoardCellValue.CROSS) {
            boardItems[cellNo] = BoardCellValue.CROSS
            if (checkForVictory(BoardCellValue.CROSS)) {
                _state.value = _state.value.copy(
                    gameStateHint = GameStateHint.WIN,
                    currentTurn = BoardCellValue.NONE,
                    playerCrossWinCount = _state.value.playerCrossWinCount + 1
                )
            } else if (gameHasDrawn()) {
                showToastMessage(context,"It's a draw!")
                _state.value = _state.value.copy(
                    gameStateHint = GameStateHint.DRAW,
                    currentTurn = BoardCellValue.NONE
                )
            }
            _state.value = _state.value.copy(
                currentTurn = BoardCellValue.CIRCLE
            )
        }
    }

    private fun gameHasDrawn(): Boolean {
        return !boardItems.values.contains(BoardCellValue.NONE)
    }

    private fun checkForVictory(boardCellValue: BoardCellValue): Boolean {
        when {
            boardItems[1] == boardCellValue && boardItems[2] == boardCellValue && boardItems[3] == boardCellValue -> {
                _state.value = _state.value.copy(victoryType = VictoryType.FirstWinHorizontalLine)
                return true
            }
            boardItems[4] == boardCellValue && boardItems[5] == boardCellValue && boardItems[6] == boardCellValue -> {
                _state.value = _state.value.copy(victoryType = VictoryType.SecondWinHorizontalLine)
                return true
            }
            boardItems[7] == boardCellValue && boardItems[8] == boardCellValue && boardItems[9] == boardCellValue -> {
                _state.value = _state.value.copy(victoryType = VictoryType.ThirdWinHorizontalLine)
                return true
            }
            boardItems[1] == boardCellValue && boardItems[4] == boardCellValue && boardItems[7] == boardCellValue -> {
                _state.value = _state.value.copy(victoryType = VictoryType.FirstWinVerticalLine)
                return true
            }
            boardItems[2] == boardCellValue && boardItems[5] == boardCellValue && boardItems[8] == boardCellValue -> {
                _state.value = _state.value.copy(victoryType = VictoryType.SecondWinVerticalLine)
                return true
            }
            boardItems[3] == boardCellValue && boardItems[6] == boardCellValue && boardItems[9] == boardCellValue -> {
                _state.value = _state.value.copy(victoryType = VictoryType.ThirdWinVerticalLine)
                return true
            }
            boardItems[1] == boardCellValue && boardItems[5] == boardCellValue && boardItems[9] == boardCellValue -> {
                _state.value = _state.value.copy(victoryType = VictoryType.FirstWinDiagonalLine)
                return true
            }
            boardItems[3] == boardCellValue && boardItems[5] == boardCellValue && boardItems[7] == boardCellValue -> {
                _state.value = _state.value.copy(victoryType = VictoryType.SecondWinDiagonalLine)
                return true
            }
            else -> {
                return false
            }
        }
    }

    private fun showToastMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}