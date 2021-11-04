package com.luisma.jetpackcomposecrash.ui.demos

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luisma.jetpackcomposecrash.R
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.sign

@ExperimentalComposeUiApi
@Composable
fun VolumenKnobScreen() {

    var activeBars by rememberSaveable {
        mutableStateOf(0)
    }
    val bars by rememberSaveable {
        mutableStateOf(20)
    }

    fun onValueChange(percentage: Int) {
        activeBars = (percentage * bars) / 100
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = Color.Green
                )
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            VolumenKnob(
                onValueChange = ::onValueChange,
                modifier = Modifier
                    .fillMaxWidth(.4F)
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            VolumenBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                activeBars = activeBars,
                bars = bars
            )
        }
    }

}

@Composable
private fun VolumenBar(
    modifier: Modifier = Modifier,
    activeBars: Int = -1,
    bars: Int = 20
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = constraints.maxWidth / (2 * bars).toFloat()

        Canvas(modifier = modifier) {
            for (i in 0 until bars) {
                drawRoundRect(
                    color = if (i <= activeBars - 1) Color.Green else Color.DarkGray,
                    topLeft = Offset(x = i * barWidth * 2, y = 0F),
                    size = Size(width = barWidth, height = constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(10F)
                )
            }
        }
    }

}

@ExperimentalComposeUiApi
@Composable
private fun VolumenKnob(
    modifier: Modifier = Modifier,
    limitAngle: Float = 25f,
    onValueChange: (porcentage: Int) -> Unit
) {

    var centerX by rememberSaveable {
        mutableStateOf(0f)
    }

    var centerY by rememberSaveable {
        mutableStateOf(0f)
    }

    var rotation by rememberSaveable {
        mutableStateOf(limitAngle)
    }

    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.music_knob),
            contentDescription = "Music knob",
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    val size = it.boundsInWindow().size
                    centerX = size.width / 2f
                    centerY = size.height / 2f
                }
                .pointerInteropFilter {
                    val touchX = it.x
                    val touchY = it.y
                    var alfa =
                        atan2(
                            y = touchY - centerY,
                            x = touchX - centerX,
                        ) * (180f / PI).toFloat() + 90F

                    alfa = if (alfa.toInt().sign == -1) {
                        360F + alfa
                    } else alfa


                    val output = when (it.action) {
                        MotionEvent.ACTION_DOWN -> true
                        MotionEvent.ACTION_MOVE -> {

                            val limitMax = 360 - limitAngle

                            when {
                                (alfa >= limitMax) -> {
                                    false
                                }
                                (alfa <= limitAngle) -> {
                                    false
                                }
                                else -> {
                                    rotation = alfa
                                    val pendiente = 100 / (360F - 2 * limitAngle)
                                    val percentage =
                                        (pendiente * rotation - pendiente * limitAngle).toInt()
                                    onValueChange(percentage + 1)
                                    true
                                }
                            }

                        }
                        else -> false
                    }

                    output
                }
                .rotate(rotation)
        )
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
private fun VolumenKnobPreview() {
    VolumenKnobScreen()
}