package com.example.drujite.presentation.session_selection

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.models.SessionModel
import com.example.drujite.R
import com.example.drujite.presentation.LoadingScreen
import com.example.drujite.presentation.MyButton
import com.example.drujite.presentation.MyTitle
import com.example.drujite.presentation.MyTitle2
import com.example.drujite.presentation.Screen
import com.example.drujite.presentation.startQRScanner
import org.koin.androidx.compose.koinViewModel

@Composable
fun SessionView(
    navController: NavController,
    userId: Int,
    viewModel: SessionViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()
    val context = LocalContext.current

    when (val action = viewAction.value) {
        is SessionAction.NavigateToCharacterCreation -> {
            viewModel.clearAction()
            navController.navigate("${Screen.CharacterCreation.route}/${action.sessionId}/${userId}") {
                popUpTo("${Screen.SessionSelection.route}/${userId}") { inclusive = true }
                launchSingleTop = true
            }
        }

        is SessionAction.StartQRScanner -> {
            startQRScanner(context) { result, sessionNum ->
                viewModel.obtainEvent(SessionEvent.OnQRScannerClosed(result, sessionNum))
            }
        }

        else -> {}
    }

    when (val state = viewState.value) {
        is SessionState.Main -> {
            MainState(
                state = state,
                onQRCodeClicked = { viewModel.obtainEvent(SessionEvent.QRScannerClicked) },
                onProceedClicked = { viewModel.obtainEvent(SessionEvent.SessionpRroceed(it)) }
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
    val currentItem = remember { mutableStateOf<Item>(items[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 24.dp),
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
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalMultiBrowseCarousel(
                state = carouselState,
                modifier = Modifier.padding(40.dp, 20.dp),
                preferredItemWidth = 260.dp,
                itemSpacing = 16.dp,
            ) { i ->
                val item = items[i]
                CarouselItem(
                    item = item,
                    currentItem = currentItem.value,
                    onItemSelected = { item ->
                        currentItem.value = item
                    },
                    qrError = state.qrError
                )
            }
        }
        Box(
            modifier = Modifier
        ) {
            MyButton("Выбрать") {
                if (currentItem.value.id == 0) {
                    onQRCodeClicked()
                } else {
                    currentItem.value.session?.let { onProceedClicked(it) }
                }
            }
        }
    }
}

@Composable
fun CarouselItem(
    item: Item,
    currentItem: Item,
    onItemSelected: (Item) -> Unit,
    qrError: String
) {
    val borderColor =
        if (item.id == currentItem.id) MaterialTheme.colorScheme.primary else Color.Black
    val title = if (item.session == null) qrError else item.session.name
    val text = item.session?.description ?: ""
    Column {
        Image(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(32.dp))
                .border(1.dp, borderColor, RoundedCornerShape(32.dp))
                .clickable {
                    onItemSelected(item)
                },
            painter = painterResource(id = item.imageResId),
            contentDescription = "GreetingImage",
            contentScale = ContentScale.Crop
        )
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(4.dp),
            maxLines = 1,
            minLines = 1,
            overflow = TextOverflow.Ellipsis

        )
        Text(
            text = text,
            fontSize = 15.sp,
            modifier = Modifier.padding(4.dp),
            maxLines = 5,
            minLines = 5,
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
