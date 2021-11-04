package com.luisma.jetpackcomposecrash.ui.demos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ScrollableColumn() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    fun goToEnd() {
        coroutineScope.launch {
            scrollState.scrollTo(scrollState.maxValue)
        }
    }

    fun goToStart() {
        coroutineScope.launch {
            scrollState.scrollTo(0)
        }
    }


    Column(
        Modifier.fillMaxSize()
    ) {
        //Buttons
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(color = Color.Black)
                .padding(vertical = 12.dp)
                .fillMaxWidth()
        ) {
            MenuButton(onTap = ::goToStart, "Start")
            MenuButton(onTap = ::goToEnd, "End")
        }
        //ScrollableColumn
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
        ) {
            repeat(20) {
                ListItem(index = it)
            }
        }
    }
}

@Composable
private fun MenuButton(onTap: () -> Unit, text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(start = 10.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(5.dp)
            )
            .clickable { onTap() }
    ) {
        Text(
            text,
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun ListItem(index: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(
                color = Color(
                    red = Random.nextFloat(),
                    blue = Random.nextFloat(),
                    green = Random.nextFloat()
                ),
            )
    ) {
//        Text(
//            "Item $index",
//            color = Color.White,
//            fontSize = 20.sp,
//        )
    }
}

@Composable
@Preview
fun ScrollableColumnPreview() {
    ScrollableColumn()
}


