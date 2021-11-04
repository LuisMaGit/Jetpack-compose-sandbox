package com.luisma.jetpackcomposecrash.ui.demos

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgressIndicatorScreen() {

    val target = 80F
    var percentage by remember {
        mutableStateOf(0F)
    }

    val percentageAnimation by animateFloatAsState(
        targetValue = percentage,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutLinearInEasing
        )
    )

    fun startAnimation() {
        if (percentage == 0F) {
            percentage = target
            return
        }
        percentage = 0F
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        CircularProgressIndicator(
            percentage = percentageAnimation,
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = ::startAnimation) {
            Text(text = "Animate")
        }
    }
}


@Composable
private fun CircularProgressIndicator(
    radius: Dp = 100.dp,
    fontSize: TextUnit = 40.sp,
    percentage: Float = 0F,
    strokeWidth: Dp = 8.dp,
    color: Color = Color.Green
) {

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier.size(radius * 2F)
        ) {
            drawArc(
                startAngle = -90F,
                sweepAngle = percentage * 3.6F,
                color = color,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Text(
            text = "${percentage.toInt()}",
            fontWeight = FontWeight.Bold,
            fontSize = fontSize
        )
    }

}

@Preview
@Composable
fun CircularProgressIndicatorPreview() {
    CircularProgressIndicatorScreen()
}
