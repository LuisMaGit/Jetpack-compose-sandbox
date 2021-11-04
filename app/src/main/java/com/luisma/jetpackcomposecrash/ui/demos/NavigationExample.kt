package com.luisma.jetpackcomposecrash.ui.demos

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.luisma.jetpackcomposecrash.navigation.Routes

@Composable
fun MainScreen(navController: NavController) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    fun setText(value: String) {
        text = value
    }

    fun goToDetails() {
        val arg = if (text.isEmpty()) "No value passed" else text
        navController.navigate(route = Routes.DetailsScreen.withArg(arg))
    }

    fun goToDetailsWithOptional() {
        navController.navigate(route = Routes.DetailsScreen.route + "?text=$text")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text, onValueChange = ::setText
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = ::goToDetails
        ) {
            Text(text = "To details")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = ::goToDetailsWithOptional
        ) {
            Text(text = "To details with optional")
        }
    }
}

@Composable
fun DetailsScreen(text: String?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = text ?: "")
    }
}