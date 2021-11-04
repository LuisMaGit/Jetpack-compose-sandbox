package com.luisma.jetpackcomposecrash.ui.demos

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CircularTimerScreen() {
    val time = 100
    val coroutine = rememberCoroutineScope()
    var seconds by remember {
        mutableStateOf(time)
    }

    var isRuning by remember {
        mutableStateOf(false)
    }


    fun stop() {
        isRuning = false
    }

    suspend fun timer() {
        while (isRuning) {
            seconds--
            delay(1000L)

            if (seconds == 0) {
                seconds = time
                stop()
            }
        }
    }


    fun start() {
        isRuning = true
        coroutine.launch {
            timer()
        }
    }

    fun onCLick() {
        if (isRuning) {
            stop()
            return
        }
        start()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularTimer(
            time = time,
            ticker = seconds,
        )
        Button(
            onClick = ::onCLick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isRuning) Color.Red else Color.Green
            )
        ) {
            Text(text = if (isRuning) "Stop" else "Count")
        }
    }

}

@Composable
private fun CircularTimer(
    ticker: Int,
    time: Int,
    radius: Dp = 100.dp,
    strokeWidth: Dp = 8.dp,
    handleWidth: Dp = 20.dp,
    activeBarColor: Color = Color(0XFF37B900),
    unActiveBarColor: Color = Color.DarkGray,
    handleColor: Color = Color.Green,
) {
    val startAngle = -210F
    val sweepStart = 240F //240F - 0F
    val sweepAngle = sweepStart / time * ticker

    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(radius * 2)) {
            drawArc(
                color = unActiveBarColor,
                startAngle = startAngle,
                sweepAngle = sweepStart,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = activeBarColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            val centerX = size.width / 2
            val centerY = size.height / 2
            val beta = (sweepAngle - 210) * PI / 180

            val x = radius.toPx() * cos(beta)
            val y = radius.toPx() * sin(beta)

            drawPoints(
                points = listOf(
                    Offset(x = x.toFloat() + centerX, y = y.toFloat() + centerY)
                ),
                pointMode = PointMode.Points,
                cap = StrokeCap.Round,
                strokeWidth = handleWidth.toPx(),
                color = handleColor,
            )
        }

        Text(text = ticker.toString(), fontSize = 32.sp)

    }


}


@Preview
@Composable
fun CircularTimerPreview() {
    CircularTimerScreen()
}