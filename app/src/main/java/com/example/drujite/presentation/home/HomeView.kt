package com.example.drujite.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.models.CharacterModel
import com.example.domain.models.GoalModel
import com.example.drujite.R
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyTitle
import com.example.drujite.presentation.my_composables.MyTitle2
import com.example.drujite.presentation.Screen
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
            navController.navigate(Screen.CharacterCustomisation.route)
            viewModel.clearAction()
        }

        else -> {}
    }

    when (val state = viewState.value) {
        is HomeState.Main -> {
            MainState(
                state = state,
                onGoalClick = { goal ->
                    viewModel.obtainEvent(HomeEvent.goalClicked(goal))
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
    val clothingItems = List(3) { R.drawable.hair }
    val goals = state.goals
    val character = state.character
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center,
                text = "Персонаж",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
            ) {
                clothingItems.forEach {
                    ClothingItem(it)
                }

            }
            Image(
                painter = painterResource(id = R.drawable.character),
                contentDescription = "Character",
                modifier = Modifier
                    .size(180.dp, 256.dp)
                //.clickable { onCustomisationClick() }
            )
            Column(
                modifier = Modifier
            ) {
                clothingItems.forEach {
                    ClothingItem(it)
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
                text = "Квента",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            ShortenedTextBig(
                text = character.story,
                maxLines = 4
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Цели",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            goals.forEach {
                GoalItem(it) {
                    onGoalClick(it)
                }
            }
        }
    }
}

@Composable
fun ClothingItem(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Clothing",
        modifier = Modifier
            .padding(vertical = 12.dp)
            .size(64.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
    )
}

@Composable
fun GoalItem(goal: GoalModel, onGoalClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
            .border(1.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(12.dp)),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = goal.isCompleted,
                onCheckedChange = { onGoalClick() },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    checkedColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = goal.goal,
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
                        goal = "Цель 1",
                        isCompleted = false
                    ),
                    GoalModel(
                        id = 2,
                        goal = "Цель 2",
                        isCompleted = true
                    ),
                    GoalModel(
                        id = 3,
                        goal = "Цель 3",
                        isCompleted = false
                    )
                )
            ),
            onGoalClick = {},
            onCustomisationClick = {}
        )
    }
}