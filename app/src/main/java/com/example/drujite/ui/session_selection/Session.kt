package com.example.drujite.ui.session_selection

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.drujite.R
import com.example.drujite.ui.MyButton
import com.example.drujite.ui.MyTitle
import com.example.drujite.ui.MyTitle2

@Composable
fun Session() {
    MainState()
}

data class CarouselItem(
    val id: Int,
    @DrawableRes val imageResId: Int,
    //@StringRes val contentDescriptionResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainState() {
    val items = listOf(
        CarouselItem(
            id = 0,
            imageResId = R.drawable.qr_code
        )
    ) + (1..10).map { index ->
        CarouselItem(
            id = index,
            imageResId = R.drawable.greeting,
        )
    }
    val carouselState = rememberCarouselState { items.count() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyTitle("Выбери смену")
            MyTitle2("Выбери одну из смен, на которых ты был, либо отсканируй QR-код новой смены.")
            HorizontalMultiBrowseCarousel(
                state = carouselState,
                modifier = Modifier.padding(40.dp, 20.dp),
                preferredItemWidth = 260.dp,
                itemSpacing = 16.dp,
            ) { i ->
                val item = items[i]
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = "GreetingImage",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(32.dp))
                        .border(1.dp, Color.Black, RoundedCornerShape(32.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Box(
            modifier = Modifier.padding(0.dp, 64.dp)
        ) {
            MyButton("Выбрать") { }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainPreview() {
    AppTheme() {
        MainState()
    }
}
