package com.luisma.jetpackcomposecrash.ui.demos

import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimationSimple() {

    val initialDp = 100.dp
    //RED BOX
    var sizeStateRedBox by remember {
        mutableStateOf(initialDp)
    }
    val sizeAnimationRedBox by
    animateDpAsState(
        targetValue = sizeStateRedBox,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing),
        finishedListener = {
            Log.v("AnimationTween", "These is the call when the animation ends with $it")
        }

    )
    //BLUE BOX
    var sizeStateBlueBox by remember {
        mutableStateOf(initialDp)
    }
    val sizeAnimationBlueBox by animateDpAsState(
        targetValue = sizeStateBlueBox,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy
        ),
        finishedListener = {
            Log.v("AnimationTween", "These is the call when the animation ends with $it")
        }
    )
    //BLACK BOX
    var sizeStateBlackBox by remember {
        mutableStateOf(initialDp)
    }
    val sizeAnimationGreeBox by animateDpAsState(
        targetValue = sizeStateBlackBox,
        keyframes {
            durationMillis = 5000
            initialDp at 0 with LinearEasing
            initialDp * 3 at 2500 with FastOutLinearInEasing
        }, finishedListener = {
            Log.v("AnimationTween", "These is the call when the animation ends with $it")
        }
    )

    //MULTICOLOR BOX
    val infiniteTransition = rememberInfiniteTransition()
    val multicolorBoxState by infiniteTransition.animateColor(
        initialValue = Color.Cyan,
        targetValue = Color.Magenta,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 500),
            repeatMode = RepeatMode.Restart
        )
    )

    var sizeStateYellowBox by remember {
        mutableStateOf(initialDp)
    }
    val snapAnimationYellowBox by animateDpAsState(
        targetValue = sizeStateYellowBox,
        animationSpec = snap(
            delayMillis = 200
        )
    )

    fun redBoxTapTap() {
        if (sizeStateRedBox == initialDp) {
            sizeStateRedBox *= 2
            return
        }
        sizeStateRedBox = initialDp
    }

    fun blueBoxTap() {
        if (sizeStateBlueBox == initialDp) {
            sizeStateBlueBox *= 2
            return
        }

        sizeStateBlueBox = initialDp
    }

    fun greenBoxTap() {
        if (sizeStateBlackBox == initialDp) {
            sizeStateBlackBox *= 2
            return
        }

        sizeStateBlackBox = initialDp
    }

    fun handleYellowBox() {
        if (sizeStateYellowBox == initialDp) {
            sizeStateYellowBox *= 2
            return
        }

        sizeStateYellowBox = initialDp
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Tween size animation")
        Box(
            modifier = Modifier
                .size(sizeAnimationRedBox)
                .background(color = Color.Red)
                .clickable { redBoxTapTap() },
            contentAlignment = Alignment.Center,
        ) {
            Text(if (sizeStateRedBox == initialDp) "Grow" else "Shrink", color = Color.White)
        }
        Text("Spring size Animation")
        Box(
            modifier = Modifier
                .size(sizeAnimationBlueBox)
                .background(color = Color.Blue)
                .clickable { blueBoxTap() },
            contentAlignment = Alignment.Center,
        ) {
            Text(if (sizeStateBlueBox == initialDp) "Grow" else "Shrink", color = Color.White)
        }
        Text("Key frames animation")
        Box(
            modifier = Modifier
                .size(sizeAnimationGreeBox)
                .background(color = Color.Black)
                .clickable { greenBoxTap() },
            contentAlignment = Alignment.Center,
        ) {
            Column {
                Text(
                    if (sizeAnimationGreeBox == initialDp) "Grow" else "Shrink",
                    color = Color.White
                )
                Text(
                    sizeAnimationGreeBox.toString(),
                    color = Color.White
                )
            }
        }
        Text("Infinite color animation")
        Box(
            modifier = Modifier
                .size(sizeAnimationGreeBox)
                .background(color = multicolorBoxState),
            contentAlignment = Alignment.Center,
        ) {
        }
        Text("Snap size Animation with delay")
        Box(
            modifier = Modifier
                .size(snapAnimationYellowBox)
                .background(color = Color.Yellow)
                .clickable { handleYellowBox() },
            contentAlignment = Alignment.Center,
        ) {
            Text("Grow")
        }

    }
}


