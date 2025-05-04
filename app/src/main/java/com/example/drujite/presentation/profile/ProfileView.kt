package com.example.drujite.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.models.CharacterModel
import com.example.domain.models.SessionModel
import com.example.domain.models.UserModel
import com.example.drujite.R
import com.example.drujite.presentation.Screen
import com.example.drujite.presentation.my_composables.BottomSheetCharacter
import com.example.drujite.presentation.my_composables.LazyGridCharacters
import com.example.drujite.presentation.my_composables.LazyGridSessions
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyCard
import com.example.drujite.presentation.my_composables.MyTextButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileView(
    navController: NavController,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (val action = viewAction.value) {
        is ProfileAction.NavigateToGreeting -> {
            navController.navigate(Screen.Greeting.route) {
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
            viewModel.clearAction()
        }

        is ProfileAction.NavigateToMain -> {
            val sessionId = (viewAction.value as ProfileAction.NavigateToMain)
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
            viewModel.clearAction()
        }

        is ProfileAction.NavigateToCharacterCreation -> {
            val sessionId = (viewAction.value as ProfileAction.NavigateToCharacterCreation)
            navController.navigate("${Screen.CharacterCreation.route}/${action.sessionId}/${action.userToken}") {
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
            viewModel.clearAction()
        }

        else -> {}
    }

    when (val state = viewState.value) {
        is ProfileState.Main -> {
            MainState(
                state = state,
                onLogOut = { viewModel.obtainEvent(ProfileEvent.LogOut) },
                onSessionsClicked = {
                    viewModel.obtainEvent(ProfileEvent.ShowSessions)
                },
                onCharactersClicked = {
                    viewModel.obtainEvent(ProfileEvent.ShowCharacters)
                },
                onSessionSelected = { session ->
                    viewModel.obtainEvent(ProfileEvent.ChangeSession(session.id))
                }
            )
        }

        is ProfileState.Initialization -> {
            viewModel.obtainEvent(ProfileEvent.LoadData)
            LoadingScreen()
        }

        is ProfileState.Loading -> {
            LoadingScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainState(
    state: ProfileState.Main,
    onLogOut: () -> Unit,
    onSessionsClicked: () -> Unit,
    onCharactersClicked: () -> Unit,
    onSessionSelected: (SessionModel) -> Unit,
) {
    val user = state.user
    val sessions = state.sessions
    val characters = state.characters
    val showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val chosenCharacter = remember { mutableStateOf<CharacterModel?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Header(user = user, onLogOut = onLogOut)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            MyNumCard(
                modifier = Modifier.weight(1f),
                num = sessions.size,
                title = stringResource(R.string.profile_sessions),
                onClick = onSessionsClicked
            )
            MyNumCard(
                modifier = Modifier.weight(1f),
                num = characters.size,
                title = stringResource(R.string.profile_characters),
                onClick = onCharactersClicked
            )
        }
        if (state.showingCharacters) {
            LazyGridCharacters(
                characters = characters,
                onCharacterClick = { character ->
                    chosenCharacter.value = character
                    showBottomSheet.value = true
                }
            )
        } else {
            LazyGridSessions(
                sessions = state.sessions,
                onSessionClick = onSessionSelected
            )
        }
        if (showBottomSheet.value) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet.value = false },
                sheetState = sheetState
            ) {
                chosenCharacter.value?.let { BottomSheetCharacter(character = it) }
            }
        }
    }
}

@Composable
fun Header(user: UserModel, onLogOut: () -> Unit) {
    Spacer(modifier = Modifier.height(32.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        MyTextButton(
            text = stringResource(R.string.logout),
            onClick = onLogOut
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = user.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = user.phone,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun MyNumCard(num: Int, title: String, modifier: Modifier, onClick: () -> Unit) {
    MyCard(
        modifier = modifier.padding(horizontal = 8.dp),
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = num.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfilePreview() {
    AppTheme {
        MainState(
            state = ProfileState.Main(
                user = UserModel(
                    token = "1",
                    name = "Анастасия Чеботарь",
                    phone = "+7 999 999 99 99"
                ),
                sessions = emptyList(),
                characters = listOf(
                    CharacterModel(
                        id = 1,
                        name = "Персонаж",
                        player = "Игрок 1",
                        story = "тут будет квента",
                        imageUrl = "",
                        clan = "Янтарная Ветвь"
                    ),
                    CharacterModel(
                        id = 2,
                        name = "Персонаж 2",
                        player = "Игрок 1",
                        story = "",
                        imageUrl = "",
                        clan = "Ониксовая Ветвь"
                    ),
                    CharacterModel(
                        id = 3,
                        name = "Персонаж 3",
                        player = "Игрок 1",
                        story = "",
                        imageUrl = "",
                        clan = "Яшмовая ветвь"
                    )
                )
            ),
            onLogOut = {},
            onSessionsClicked = {},
            onCharactersClicked = {},
            onSessionSelected = {}
        )
    }
}