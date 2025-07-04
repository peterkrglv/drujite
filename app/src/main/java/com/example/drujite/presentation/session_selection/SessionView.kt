package com.example.drujite.presentation.session_selection

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.compose.AppTheme
import com.example.domain.models.SessionModel
import com.example.drujite.R
import com.example.drujite.presentation.Screen
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyButton
import com.example.drujite.presentation.my_composables.MyTitle
import com.example.drujite.presentation.my_composables.MyTitle2
import com.example.drujite.presentation.startQRScanner
import org.koin.androidx.compose.koinViewModel
import kotlin.math.absoluteValue

@Composable
fun SessionView(
    navController: NavController,
    userToken: String,
    viewModel: SessionViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()
    val context = LocalContext.current

    when (val action = viewAction.value) {
        is SessionAction.NavigateToCharacterCreation -> {
            viewModel.clearAction()
            navController.navigate("${Screen.CharacterCreation.route}/${action.sessionId}/${userToken}") {
                popUpTo("${Screen.SessionSelection.route}/${userToken}") { inclusive = true }
                launchSingleTop = true
            }
        }

        is SessionAction.NavigateToMain -> {
            viewModel.clearAction()
            navController.navigate(Screen.Home.route) {
                popUpTo("${Screen.SessionSelection.route}/${userToken}") { inclusive = true }
                launchSingleTop = true
            }
        }

        is SessionAction.StartQRScanner -> {
            startQRScanner(context) { result, sessionNum ->
                viewModel.obtainEvent(SessionEvent.OnQRScannerClosed(result, sessionNum))
            }
        }

        null -> {}
    }

    when (val state = viewState.value) {
        is SessionState.Main -> {
            MainState(
                state = state,
                onQRCodeClicked = { viewModel.obtainEvent(SessionEvent.QRScannerClicked) },
                onProceedClicked = { viewModel.obtainEvent(SessionEvent.SessionProceed(it)) }
            )
        }

        is SessionState.Loading -> {
            viewModel.obtainEvent(SessionEvent.LoadSessions(userToken))
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
            id = 0
        )
    ) + state.sessions.mapIndexed { index, session ->
        Item(
            id = index + 1,
            session = session
        )
    }
    val carouselState = rememberCarouselState { items.count() }
    val currentItem = remember { mutableStateOf(items[0]) }

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
            MyTitle(stringResource(R.string.session_selection_title))
            MyTitle2(stringResource(R.string.session_selection_subtitle))
            Spacer(modifier = Modifier.height(8.dp))
            SessionsCarousel(
                items = items.map { item ->
                    {
                        CarouselItem(
                            item = item,
                            currentItem = currentItem.value,
                            onItemSelected = {
                                currentItem.value = it
                            },
                            qrError = state.qrError
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Box(
            modifier = Modifier
        ) {
            MyButton(stringResource(R.string.session_proceed)) {
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
    val painter = when {
        item.session == null -> painterResource(id = R.drawable.qr_code)
        item.session.imageUrl != null -> rememberAsyncImagePainter(model = item.session.imageUrl)
        else -> painterResource(id = R.drawable.greeting)
    }

    if (item.session != null) {
        Log.d("imageUrl", "${item.session.imageUrl}")
    }

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
            painter = painter,
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(4.dp),
            maxLines = 1,
            minLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily
        )
        Text(
            text = text,
            fontSize = 15.sp,
            modifier = Modifier.padding(4.dp),
            maxLines = 5,
            minLines = 5,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 18.sp,
            fontFamily = MaterialTheme.typography.titleSmall.fontFamily
        )
    }
}

@Composable
fun SessionsCarousel(
    items: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState { items.size }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth(),
        pageSpacing = 24.dp,
        contentPadding = PaddingValues(horizontal = 64.dp)
    ) { page ->
        val imageModifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                val pageOffset =
                    (pagerState.currentPage - page + pagerState.currentPageOffsetFraction).absoluteValue
                lerp(
                    start = 75.dp,
                    stop = 100.dp,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleY = scale / 100.dp
                    scaleX = scale / 100.dp
                }
            }

        val item = items[page]
        Column(
            modifier = imageModifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                item()
            }
        }
    }
}

data class Item(
    val id: Int,
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
