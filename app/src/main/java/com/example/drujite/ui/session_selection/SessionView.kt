package com.example.drujite.ui.session_selection

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.SessionModel
import com.example.drujite.R
import com.example.drujite.ui.LoadingScreen
import com.example.drujite.ui.MyButton
import com.example.drujite.ui.MyTitle
import com.example.drujite.ui.MyTitle2
import org.koin.androidx.compose.koinViewModel

@Composable
fun SessionView(
    navController: NavController,
    viewModel: SessionViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (val action = viewAction.value) {
        is SessionAction.NavigateToCharacterCreation -> {
            //navController.navigate("character_creation")
            Log.d("aaaa", "SessionView: ${action.session}")
            viewModel.clearAction()
        }

        else -> {}
    }

    when (val state = viewState.value) {
        is SessionState.Main -> {
            MainState(
                state = state,
                onQRCodeClicked = { },
                onProceedClicked = { viewModel.obtainEvent(SessionEvent.SessionpRoceed(it)) }
            )
        }

        is SessionState.Loading -> {
            viewModel.obtainEvent(SessionEvent.LoadSessions)
            LoadingScreen()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainState(
    state: SessionState.Main,
    onQRCodeClicked: () -> Unit,
    onProceedClicked: (SessionModel) -> Unit
) {
    val items = listOf(
        Item(
            id = 0,
            imageResId = R.drawable.qr_code
        )
    ) + state.sessions.mapIndexed { index, session ->
        Item(
            id = index + 1,
            imageResId = R.drawable.greeting,
            session = session
        )
    }
    val carouselState = rememberCarouselState { items.count() }
    val chosenSession = remember { mutableStateOf<SessionModel?>(null) }

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
                .weight(1f, true),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyTitle("Выбери смену")
            MyTitle2("Выбери одну из смен, на которых ты был, либо отсканируй QR-код новой смены.")
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalMultiBrowseCarousel(
                state = carouselState,
                modifier = Modifier.padding(40.dp, 20.dp),
                preferredItemWidth = 260.dp,
                itemSpacing = 16.dp,
            ) { i ->
                val item = items[i]
                CarouselItem(item) {
                    chosenSession.value = it
                }
            }
        }
        Box(
            modifier = Modifier.padding(bottom = 64.dp)
        ) {
            MyButton("Выбрать") {
                chosenSession.value?.let { onProceedClicked(it) }
            }
        }
    }
}

@Composable
fun CarouselItem(item: Item, onSessionSelected: (SessionModel) -> Unit) {
    Column {
        Column(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(32.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(32.dp))
                .clickable {
                    if (item.id == 0) {
                        Log.d("aaaa", "QR code")
                    } else {
                        item.session?.let { onSessionSelected(it) }
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = "GreetingImage",
                contentScale = ContentScale.Crop
            )

        }
        Text(
            text = item.session?.name ?: "",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(8.dp),
            maxLines = 1,
            minLines = 1,
            overflow = TextOverflow.Ellipsis

        )
        Text(
            text = item.session?.description ?: "",
            fontSize = 15.sp,
            modifier = Modifier.padding(8.dp),
            maxLines = 3,
            minLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

data class Item(
    val id: Int,
    @DrawableRes val imageResId: Int,
    val session: SessionModel? = null
)

@Preview(
    showSystemUi = true,
    device = Devices.PIXEL_7_PRO
)
@Composable
fun MainPreview() {
    AppTheme {
        MainState(
            state = SessionState.Main(),
            onQRCodeClicked = { },
            onProceedClicked = { }
        )
    }
}
