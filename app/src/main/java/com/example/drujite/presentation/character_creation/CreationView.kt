package com.example.drujite.presentation.character_creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.models.ClanModel
import com.example.drujite.R
import com.example.drujite.presentation.Screen
import com.example.drujite.presentation.my_composables.DropdownTextField
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyButton
import com.example.drujite.presentation.my_composables.MyTextField
import com.example.drujite.presentation.my_composables.MyTitle
import com.example.drujite.presentation.my_composables.MyTitle2
import com.example.drujite.presentation.my_composables.TextButtonNavigation
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreationView(
    navController: NavController,
    sessionId: Int,
    userToken: String,
    viewModel: CreationViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (val action = viewAction.value) {
        is CreationAction.NavigateToCustomisation -> {
            viewModel.clearAction()
            navController.navigate("${Screen.CharacterCustomisation.route}/${userToken}/${sessionId}/${action.characterId}") {
                popUpTo("${Screen.CharacterCreation.route}/${sessionId}/${userToken}") {
                    inclusive = true
                }
            }
        }

        is CreationAction.NavigateToTransfer -> {
            viewModel.clearAction()
            navController.navigate("${Screen.CharacterTransfer.route}/${sessionId}/${userToken}")
        }

        else -> {}
    }

    when (val state = viewState.value) {
        is CreationState.Main -> {
            MainState(
                state = state,
                onNameChanged = { viewModel.obtainEvent(CreationEvent.NameChanged(it)) },
                onClanChosen = { viewModel.obtainEvent(CreationEvent.ClanChosen(it)) },
                onTransferClicked = { viewModel.obtainEvent(CreationEvent.TransferClicked) },
                onProceedClicked = { viewModel.obtainEvent(CreationEvent.ProceedClicked) }
            )
        }

        is CreationState.Initialization -> {
            viewModel.obtainEvent(CreationEvent.LoadClans(sessionId))
            LoadingScreen()
        }

        is CreationState.Loading -> {
            LoadingScreen()
        }
    }
}

@Composable
fun MainState(
    state: CreationState.Main,
    onNameChanged: (String) -> Unit,
    onClanChosen: (ClanModel) -> Unit,
    onTransferClicked: () -> Unit,
    onProceedClicked: () -> Unit,
) {
    val name = state.name
    val chosenClan = state.chosenClan
    val clans = state.clans

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 160.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            MyTitle(text = stringResource(R.string.character_creation_title))
            MyTitle2(text = stringResource(R.string.character_creation_subtitle))
            Spacer(modifier = Modifier.height(32.dp))
            MyTextField(
                value = name,
                label = stringResource(R.string.character_name_and_surname),
                errorText = null,
                onValueChange = { onNameChanged(it) }
            )
            DropdownTextField(
                label = stringResource(R.string.choose_clan),
                options = clans.map { it.name },
                selected = chosenClan?.name ?: ""
            ) { selectedName ->
                val selectedClan = clans.find { it.name == selectedName }
                onClanChosen(selectedClan!!)
            }
            MyButton(text = stringResource(R.string.next), onClick = onProceedClicked)
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            TextButtonNavigation(
                text = "Хочешь перенести персонажа?",
                buttonText = "Перенос",
                onClick = onTransferClicked
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun CreationPreview() {
    AppTheme {
        MainState(
            state = CreationState.Main(
                name = "Имя",
                chosenClan = null,
                clans = listOf(
                    ClanModel(1, "Клан 1", "url"),
                    ClanModel(2, "Клан 2", "url"),
                    ClanModel(3, "Клан 3", "url"),
                ),
                error = "",
                sessionId = 0
            ),
            onProceedClicked = {},
            onClanChosen = {},
            onNameChanged = {},
            onTransferClicked = {}
        )
    }
}