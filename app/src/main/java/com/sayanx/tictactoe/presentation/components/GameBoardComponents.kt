package com.sayanx.tictactoe.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sayanx.tictactoe.ui.theme.Aqua
import com.sayanx.tictactoe.ui.theme.GreenishYellow

@Composable
fun GameBoard(screenWidth: Dp) {
    Canvas(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onSecondary)
            .size(screenWidth)
            .padding(10.dp),
    ) {
        drawLine(
            color = Color.Gray.copy(0.2f),
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width / 3, y = 0f),
            end = Offset(x = size.width / 3, y = size.height)
        )
        drawLine(
            color = Color.Gray.copy(0.2f),
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 2 / 3, y = 0f),
            end = Offset(x = size.width * 2 / 3, y = size.height)
        )
        drawLine(
            color = Color.Gray.copy(0.2f),
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height / 3),
            end = Offset(x = size.width, y = size.height / 3)
        )
        drawLine(
            color = Color.Gray.copy(0.2f),
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 2 / 3),
            end = Offset(x = size.width, y = size.height * 2 / 3)
        )
    }
}

@Composable
fun Circle(modifier: Modifier) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = Aqua,
            style = Stroke(width = size.width/5)
        )
    }
}

@Composable
fun Cross(modifier: Modifier) {
    Canvas(modifier = modifier) {
        drawLine(
            color = GreenishYellow,
            strokeWidth = size.width/3.5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height)
        )
        drawLine(
            color = GreenishYellow,
            strokeWidth = size.width/3.5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f)
        )
    }
}

@Composable
fun FirstWinHorizontalLine(
    screenWidth: Dp = 300.dp,
    color: Color = MaterialTheme.colorScheme.error
) {
    Canvas(
        modifier = Modifier
            .size(screenWidth)
            .padding(horizontal = 10.dp)
    ) {
        drawLine(
            color = color,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height / 6),
            end = Offset(x = size.width, y = size.height / 6)
        )
    }
}

@Composable
fun SecondWinHorizontalLine(
    screenWidth: Dp,
    color: Color = MaterialTheme.colorScheme.error
) {
    Canvas(
        modifier = Modifier
            .size(screenWidth)
            .padding(horizontal = 10.dp)
    ) {
        drawLine(
            color = color,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height / 2),
            end = Offset(x = size.width, y = size.height / 2)
        )
    }
}

@Composable
fun ThirdWinHorizontalLine(
    screenWidth: Dp,
    color: Color = MaterialTheme.colorScheme.error
) {
    Canvas(
        modifier = Modifier
            .size(screenWidth)
            .padding(horizontal = 10.dp)
    ) {
        drawLine(
            color = color,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height - (size.height / 6)),
            end = Offset(x = size.width, y = size.height - (size.height / 6))
        )
    }
}

@Composable
fun FirstWinVerticalLine(
    screenWidth: Dp,
    color: Color = MaterialTheme.colorScheme.error
) {
    Canvas(
        modifier = Modifier
            .size(screenWidth)
            .padding(vertical = 10.dp)
    ) {
        drawLine(
            color = color,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width / 6, y = 0f),
            end = Offset(x = size.width / 6, y = size.height)
        )
    }
}

@Composable
fun SecondWinVerticalLine(
    screenWidth: Dp,
    color: Color = MaterialTheme.colorScheme.error
) {
    Canvas(
        modifier = Modifier
            .size(screenWidth)
            .padding(vertical = 10.dp)
    ) {
        drawLine(
            color = color,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width / 2, y = 0f),
            end = Offset(x = size.width / 2, y = size.height)
        )
    }
}

@Composable
fun ThirdWinVerticalLine(
    screenWidth: Dp,
    color: Color = MaterialTheme.colorScheme.error
) {
    Canvas(
        modifier = Modifier
            .size(screenWidth)
            .padding(vertical = 10.dp)
    ) {
        drawLine(
            color = color,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width - (size.width / 6), y = 0f),
            end = Offset(x = size.width - (size.width / 6), y = size.height)
        )
    }
}

@Composable
fun FirstWinDiagonalLine(
    screenWidth: Dp,
    color: Color = MaterialTheme.colorScheme.error
) {
    Canvas(
        modifier = Modifier
            .size(screenWidth)
            .padding(20.dp)
    ) {
        drawLine(
            color = color,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height)
        )
    }
}

@Composable
fun SecondWinDiagonalLine(
    screenWidth: Dp,
    color: Color = MaterialTheme.colorScheme.error
) {
    Canvas(
        modifier = Modifier
            .size(screenWidth)
            .padding(20.dp)
    ) {
        drawLine(
            color = color,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f),
        )
    }
}
