package com.luisma.jetpackcomposecrash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import com.luisma.jetpackcomposecrash.ui.demos.InstagramScreen
import com.luisma.jetpackcomposecrash.ui.demos.VolumenKnobScreen

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            KermitCard()
//            ColorBoxes()
//            ScrollableColumn()
//            AnimationSimple()
//            Navigator()
//            CircularProgressIndicatorScreen()
//            CircularTimerScreen()
//            InstagramScreen()
            VolumenKnobScreen()
        }
    }

}

