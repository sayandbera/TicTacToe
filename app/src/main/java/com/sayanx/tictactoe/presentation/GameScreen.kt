package com.sayanx.tictactoe.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sayanx.tictactoe.presentation.components.Circle
import com.sayanx.tictactoe.presentation.components.Cross
import com.sayanx.tictactoe.presentation.components.DrawVictoryLine
import com.sayanx.tictactoe.presentation.components.GameBoard

@Composable
fun GameScreen(viewModel: GameViewModel) {

    val localConfig = LocalConfiguration.current
    val screenWidth = remember { localConfig.screenWidthDp }
    val fontSize = rememberSaveable(key = screenWidth.toString()) {
        mutableFloatStateOf(screenWidth * 0.06f)
    }

    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Top Section:
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {

            Circle(modifier = Modifier.size((screenWidth/15).dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
                            append("${state.playerCircleWinCount}")
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Normal
                                )
                            ) {
                                append("   :   ")
                            }
                            append("${state.playerCrossWinCount}")
                        }
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize.floatValue.sp,
                )
            }

            Cross(modifier = Modifier.size((screenWidth/18).dp))
        }


        // Game board Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
                .aspectRatio(1f)
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.TopCenter
        ) {
            GameBoard(screenWidth = screenWidth.dp)
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                columns = GridCells.Fixed(3)
            ) {
                viewModel.boardItems.forEach { (cellNo, boardCellValue) ->
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {
                                    viewModel.onUserAction(
                                        action = GameEvent.TapedOnBoard(cellNo),
                                        context = context
                                    )
                                }
                        ) {
                            if (boardCellValue == BoardCellValue.CIRCLE) {
                                Circle(modifier = Modifier.size((screenWidth / 7).dp))
                            } else if (boardCellValue == BoardCellValue.CROSS) {
                                Cross(modifier = Modifier.size((screenWidth / 8.5).dp))
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = state.gameStateHint == GameStateHint.WIN,
                    enter = fadeIn(tween(1500))
                ) {
                    DrawVictoryLine(
                        gameState = state,
                        screenWidth = screenWidth
                    )
                }
            }
        }

        // Bellow button and text section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (
                state.gameStateHint == GameStateHint.WIN
                || state.gameStateHint == GameStateHint.DRAW
            ) {
                OutlinedButton(
                    onClick = {
                        viewModel.onUserAction(
                            action = GameEvent.RefreshButtonClick,
                            context = context
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        containerColor = MaterialTheme.colorScheme.onSecondary.copy(0.5f)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh icon",
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .size(18.dp)
                    )
                    Text(text = "Refresh")
                }
                Button(
                    onClick = {
                        viewModel.onUserAction(
                            action = GameEvent.PlayAgainButtonClick,
                            context = context
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onTertiary,
                        containerColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Text(text = "Play again")
                }
            }  else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (state.currentTurn == BoardCellValue.CROSS) {
                        Cross(modifier = Modifier.size(15.dp))
                    } else if (state.currentTurn == BoardCellValue.CIRCLE) {
                        Circle(modifier = Modifier.size(15.dp))
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = "It's your turn ",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

