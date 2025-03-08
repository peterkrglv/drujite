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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.models.ClanModel
import com.example.drujite.ui.DropdownTextField
import com.example.drujite.ui.LoadingScreen
import com.example.drujite.ui.MyButton
import com.example.drujite.ui.MyTextField
import com.example.drujite.ui.MyTitle
import com.example.drujite.ui.MyTitle2
import com.example.drujite.ui.Screen
import com.example.drujite.ui.TextButtonNavigation
import com.example.drujite.ui.character_creation.CreationAction
import com.example.drujite.ui.character_creation.CreationEvent
import com.example.drujite.ui.character_creation.CreationState
import com.example.drujite.ui.character_creation.CreationViewModel
import com.example.drujite.ui.timetable.MainView
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreationView(
    navController: NavController,
    viewModel: CreationViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (val action = viewAction.value) {
        is CreationAction.NavigateToCustomisation -> {
            viewModel.clearAction()
            navController.navigate(Screen.CharacterCustomisation.route) {
                popUpTo(Screen.CharacterCreation.route) { inclusive = true }
            }
        }

        is CreationAction.NavigateToTransfer -> {
            viewModel.clearAction()
            navController.navigate(Screen.CharacterTransfer.route)
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
            viewModel.obtainEvent(CreationEvent.LoadClans)
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
    val name = remember { mutableStateOf(state.name) }
    val chosenClan = remember { mutableStateOf(state.chosenClan) }
    val clans = remember { mutableStateOf(state.clans) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 128.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            MyTitle(text = "Создай персонажа")
            MyTitle2(text = "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне.")
            Spacer(modifier = Modifier.height(32.dp))
            MyTextField(
                value = name.value,
                label = "Имя и фамилия персонажа",
                errorText = null,
            ) {
                name.value = it
            }
            DropdownTextField(
                label = "Выбери клан",
                options = clans.value.map { it.name },
                selected = chosenClan.value?.name ?: "Выбери клан"
            ) { selectedName ->
                val selectedClan = clans.value.find { it.name == selectedName }
                chosenClan.value = selectedClan
                onClanChosen(selectedClan!!)
            }
            MyButton(text = "Дальше", onClick = onProceedClicked)
        }
        TextButtonNavigation(
            text = "Хочешь перенести персонажа?",
            buttonText = "Перенос",
            onClick = onTransferClicked
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun CreationPreview() {
    AppTheme {
        MainView()
    }
}