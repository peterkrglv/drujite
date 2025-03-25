package com.example.drujite.presentation.character_transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.models.CharacterModel
import com.example.drujite.R
import com.example.drujite.presentation.my_composables.DropdownTextField
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyButton
import com.example.drujite.presentation.my_composables.MyExpandedTextField
import com.example.drujite.presentation.my_composables.MySmallText
import com.example.drujite.presentation.my_composables.MyTitle
import com.example.drujite.presentation.my_composables.MyTitle2
import com.example.drujite.presentation.Screen
import com.example.drujite.presentation.my_composables.TextButtonNavigation
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransferView(
    navController: NavController,
    sessionId: Int,
    userId: Int,
    viewModel: TransferViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (viewAction.value) {
        is TransferAction.NavigateToMain -> {
            viewModel.clearAction()
            navController.navigate(Screen.Home.route) {
                popUpTo("${Screen.CharacterCreation.route}/${sessionId}/${userId}") {
                    inclusive = true
                }
            }
        }
        is TransferAction.NavigateToCreation -> {
            viewModel.clearAction()
            navController.popBackStack()
        }
        else -> {}
    }

    when (val state = viewState.value) {
        is TransferState.Initialization -> {
            viewModel.obtainEvent(TransferEvent.LoadCharacters(userId, sessionId))
            LoadingScreen()
        }
        is TransferState.Loading -> LoadingScreen()
        is TransferState.Main -> MainState(
            state = state,
            onCharacterChosen = { viewModel.obtainEvent(TransferEvent.CharacterChosen(it)) },
            onReasonChanged = { viewModel.obtainEvent(TransferEvent.ReasonChanged(it)) },
            onProceedClicked = { viewModel.obtainEvent(TransferEvent.ProceedClicked) },
            onCharacterCreationClicked = { viewModel.obtainEvent(TransferEvent.CharacterCreationClicked) }
        )
    }
}

@Composable
fun MainState(
    state: TransferState.Main,
    onCharacterChosen: (CharacterModel) -> Unit,
    onReasonChanged: (String) -> Unit,
    onProceedClicked: () -> Unit,
    onCharacterCreationClicked: () -> Unit
) {
    val characters = state.characters
    val chosenCharacter = state.chosenCharacter
    val reason = state.reason

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            MyTitle(text = stringResource(R.string.transfer_title))
            MyTitle2(text = stringResource(R.string.transfer_subtitle))
            DropdownTextField(
                label = stringResource(R.string.transfer_character_choice),
                options = characters.map { it.name },
                selected = chosenCharacter?.name ?: "",
                onOptionSelected = { name ->
                    val character = characters.find { it.name == name }
                    if (character != null) {
                        onCharacterChosen(character)
                    }
                }
            )
            MyExpandedTextField(
                value = reason,
                label = stringResource(R.string.transfer_chaarcter_reason),
                isError = false,
                onValueChange = { onReasonChanged(it) }
            )
            MySmallText(text = stringResource(R.string.transfer_warning))
            MyButton(text = stringResource(R.string.proceed), onClick = onProceedClicked)
        }
        TextButtonNavigation(
            text = stringResource(R.string.transfer_back),
            buttonText = stringResource(R.string.transfer_back2),
            onClick = onCharacterCreationClicked
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun TransferPreview() {
    AppTheme {
        MainState(
            state = TransferState.Main(),
            onCharacterChosen = {},
            onReasonChanged = {},
            onProceedClicked = {},
            onCharacterCreationClicked = {}
        )
    }
}