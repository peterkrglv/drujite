package com.example.drujite.presentation.character_customisation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.domain.use_cases.CustomisationCategory
import com.example.drujite.R
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyButton
import com.example.drujite.presentation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun CustomisationView(
    navController: NavController,
    userId: Int,
    sessionId: Int,
    characterId: Int,
    viewModel: CustomisationViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (val action = viewAction.value) {
        is CustomisationAction.NavigateToMain -> {
            viewModel.clearAction()
            navController.navigate(Screen.Home.route)
        }
        else -> {}
    }

    when (val state = viewState.value) {
        is CustomisationState.Initialization -> {
            viewModel.obtainEvent(CustomisationEvent.LoadOptions)
            LoadingScreen()
        }
        is CustomisationState.Loading -> LoadingScreen()
        is CustomisationState.Main -> {
            MainState(
                state = state,
                onOptionChosen = { category, num ->
                    viewModel.obtainEvent(CustomisationEvent.OptionChosen(category, num))
                },
                onProceedClicked = { viewModel.obtainEvent(CustomisationEvent.ProceedClicked(characterId)) }
            )
        }
    }

}

@Composable
fun MainState(
    state: CustomisationState.Main,
    onOptionChosen: (CustomisationCategory, Int) -> Unit,
    onProceedClicked: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                textAlign = TextAlign.Center,
                text = "Что на счет внешности?",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Image(
                painter = painterResource(id = R.drawable.character),
                contentDescription = "Character",
                modifier = Modifier
                    .padding(32.dp)
                    .size(360.dp, 510.dp)
            )
        }

        items(state.options.size) { index ->
            val category = state.options[index]
            ItemChoice(
                category = category,
                chosenItem = state.chosenOptions[category] ?: 0,
                onItemChosen = { chosenIndex -> onOptionChosen(category, chosenIndex) }
            )
        }

        item {
            MyButton(text = "Закончить", onClick = onProceedClicked)
        }
    }
}

@Composable
fun ItemChoice(category: CustomisationCategory, chosenItem: Int, onItemChosen: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = category.name,
            modifier = Modifier.padding(16.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow {
            items(category.options.size) { index ->
                Image(
                    painter = painterResource(R.drawable.character),
                    //painter = rememberImagePainter(data = category.options[index].imageUrl),
                    contentDescription = category.name,
                    modifier = Modifier
                        .padding(6.dp)
                        .size(88.dp)
                        .clickable {
                            onItemChosen(index)
                        }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CustomisationPreview() {
    AppTheme {
        MainState(
            state = CustomisationState.Main(),
            onOptionChosen = { _, _ -> },
            onProceedClicked = {}
        )
    }
}