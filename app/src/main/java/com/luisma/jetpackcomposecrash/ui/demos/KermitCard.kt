package com.luisma.jetpackcomposecrash.ui.demos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luisma.jetpackcomposecrash.R

/**
 * Simple Kermit Card with Boxes, text styles
 * */
@Composable
fun KermitCard(
) {
    //Font
    val kaiseiFont = FontFamily(
        Font(R.font.kaisei_bold, FontWeight.Bold),
        Font(R.font.kaisei_extrabold, FontWeight.ExtraBold),
        Font(R.font.kaisei_medium, FontWeight.Medium),
        Font(R.font.kaisei_regular, FontWeight.Normal),
    )

    //Image
    val image = painterResource(id = R.drawable.kermit)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .fillMaxHeight(.4f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    clip = true
                )
        ) {
            Box {
                Image(
                    painter = image,
                    contentDescription = "These is an image",
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.25f)
                        .align(alignment = Alignment.BottomStart)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                )
                            )
                        )
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Green,
                                    fontFamily = kaiseiFont,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp,
                                )
                            ) {
                                append("T")
                            }
                            append("hese is Kermit playing a banjo")
                        },
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        fontFamily = kaiseiFont,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .padding(
                                horizontal = 10.dp
                            ),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun KermitCardPreview() {
    KermitCard()
}