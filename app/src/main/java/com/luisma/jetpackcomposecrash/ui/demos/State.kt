package com.luisma.jetpackcomposecrash.ui.demos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

/**
Simple state example
 */

@Composable
fun ColorBoxes() {
    //State
    //1- remember not persist state over configuration change
    val colorBox1 = remember{
        mutableStateOf(Color.Red)
    }

    //Handler
    fun onTap() {
        colorBox1.value = Color(
            red = Random.nextFloat(),
            blue = Random.nextFloat(),
            green = Random.nextFloat()
        )
    }

    //Render
    Screen(
        onTap = ::onTap,
        colorBox1 = colorBox1.value,
    )


}


@Composable
private fun Screen(onTap: () -> Unit, colorBox1: Color) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        BoxClickable(
            modifier = Modifier
                .weight(1f)
        ) {
            onTap()
        }
        Box1(
            modifier = Modifier
                .weight(1f),
            color = colorBox1
        )
    }
}

@Composable
private fun BoxClickable(
    modifier: Modifier,
    clickable: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Red)
            .clickable { clickable() }
    ) {
        Text("Click me", color = Color.White, fontSize = 32.sp)
    }
}

@Composable
private fun Box1(modifier: Modifier, color: Color) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(color = color)
    ) {
        Text(
            "Color Changer",
            color = Color.White,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}
