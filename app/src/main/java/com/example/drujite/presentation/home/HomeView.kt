package com.example.drujite.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.compose.AppTheme
import com.example.domain.models.CharacterModel
import com.example.domain.models.GoalModel
import com.example.domain.models.SessionModel
import com.example.drujite.R
import com.example.drujite.presentation.Screen
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyCard
import com.example.drujite.presentation.my_composables.MyTitle
import com.example.drujite.presentation.my_composables.MyTitle2
import com.example.drujite.presentation.my_composables.ShortenedTextBig
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeView(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (val action = viewAction.value) {
        is HomeAction.NavigateToCustomization -> {
            navController.navigate("${Screen.CharacterCustomisation.route}/${action.userToken}/${action.sessionId}/${action.characterId}") {
                launchSingleTop = true
            }
            viewModel.clearAction()
        }
        else -> {}
    }

    when (val state = viewState.value) {
        is HomeState.Main -> {
            MainState(
                state = state,
                onGoalClick = { goal ->
                    viewModel.obtainEvent(HomeEvent.GoalClicked(goal))
                },
                onCustomisationClick = {
                    viewModel.obtainEvent(HomeEvent.CustomisationClicked)
                }
            )
        }

        is HomeState.Initialization -> {
            LoadingScreen()
            viewModel.obtainEvent(HomeEvent.LoadData)
        }

        is HomeState.Loading -> {
            LoadingScreen()
        }
    }
}

@Composable
fun MainState(
    state: HomeState.Main,
    onGoalClick: (GoalModel) -> Unit,
    onCustomisationClick: () -> Unit
) {
    val clothingItems = state.items
    val clothingItemsFirstThree = clothingItems.take(3)
    val clothingItemsNextThree = clothingItems.drop(3)
    val goals = state.goals
    val character = state.character
    var currentImageUrl by remember { mutableStateOf(character.imageUrl) }

    LaunchedEffect(clothingItems) {
        currentImageUrl = character.imageUrl?.let { "$it?t=${System.currentTimeMillis()}" }
    }

    val painter = if (currentImageUrl == null) {
        painterResource(id = R.drawable.character)
    } else {
        rememberAsyncImagePainter(model = currentImageUrl)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            MyTitle(state.session.name)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                clothingItemsFirstThree.forEach {
                    it.iconUrl?.let {
                        ClothingItem(it)
                    }
                }

            }
            Image(
                painter = painter,
                contentDescription = "Character image",
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { onCustomisationClick() },
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                clothingItemsNextThree.forEach {
                    it.iconUrl?.let {
                        ClothingItem(it)
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyTitle(text = character.name)
                MyTitle2(text = character.clan)
            }
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = stringResource(R.string.home_quent),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            )
            ShortenedTextBig(
                text = if (character.story != "") character.story else "У персонажа пока нет квенты",
                maxLines = 4
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(R.string.home_goals),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily
                )
            }
            if (goals.isEmpty()) ShortenedTextBig(
                text = if (character.story != "") character.story else "У персонажа пока нет целей",
                maxLines = 4
            ) else {
                goals.forEach {
                    GoalItem(it) {
                        onGoalClick(it)
                    }
                }
            }
        }
    }
}

@Composable
fun ClothingItem(iconUrl: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(iconUrl)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
    )
    Log.d("ClothingItem", "Icon URL: $iconUrl")
    Image(
        painter = painter,
        contentDescription = "Clothing",
        modifier = Modifier.size(70.dp)
    )
}

@Composable
fun GoalItem(goal: GoalModel, onGoalClick: () -> Unit) {
    MyCard(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = goal.isCompleted,
                onCheckedChange = { onGoalClick() },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    checkedColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    uncheckedColor = MaterialTheme.colorScheme.onSurface
                )
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = goal.name,
                fontFamily = MaterialTheme.typography.titleSmall.fontFamily
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    AppTheme {
        MainState(
            state = HomeState.Main(
                character = CharacterModel(
                    id = 1,
                    name = "Имя",
                    player = "Игрок",
                    clan = "Клан",
                    story = "Квента",
                    imageUrl = ""
                ),
                goals = listOf(
                    GoalModel(
                        id = 1,
                        name = "Цель 1",
                        isCompleted = false
                    ),
                    GoalModel(
                        id = 2,
                        name = "Цель 2",
                        isCompleted = true
                    ),
                    GoalModel(
                        id = 3,
                        name = "Цель 3",
                        isCompleted = false
                    )
                ),
                items = emptyList(),
                session = SessionModel(
                    id = 1,
                    name = "Смена",
                    description = "Описание смены",
                    dates = "01.01.2025 - 07.01.2025",
                    imageUrl = null
                )
            ),
            onGoalClick = {},
            onCustomisationClick = {}
        )
    }
}