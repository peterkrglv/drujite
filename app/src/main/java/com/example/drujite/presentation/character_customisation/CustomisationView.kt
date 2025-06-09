package com.example.drujite.presentation.character_customisation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.domain.use_cases.customisation.CustomisationCategory
import com.example.drujite.R
import com.example.drujite.presentation.Screen
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyButton
import com.example.drujite.presentation.my_composables.MyGradient
import org.koin.androidx.compose.koinViewModel

@Composable
fun CustomisationView(
    navController: NavController,
    userId: String,
    sessionId: Int,
    characterId: Int,
    viewModel: CustomisationViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.collectAsState()

    when (viewAction.value) {
        is CustomisationAction.NavigateToMain -> {
            viewModel.clearAction()
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }

        else -> {}
    }

    when (val state = viewState.value) {
        is CustomisationState.Initialization -> {
            viewModel.obtainEvent(CustomisationEvent.LoadOptions(characterId = characterId))
        }

        is CustomisationState.Loading -> {
            LoadingScreen()
        }

        is CustomisationState.Main -> {
            MainState(
                state = state,
                onOptionChosen = { category, num ->
                    viewModel.obtainEvent(CustomisationEvent.OptionChosen(category, num))
                },
                onProceedClicked = {
                    viewModel.obtainEvent(CustomisationEvent.ProceedClicked(characterId))
                }
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
    val customOptions = state.options.filter {
        state.firstCustom != it.isEditable
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.customisation_title),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(180.dp, 255.dp)
        ) {
            state.chosenOptions.forEach { (category, chosenIndex) ->
                val selectedOption = category.items[chosenIndex]
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(selectedOption.imageUrl)
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = category.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(customOptions.size) { index ->
                    val category = customOptions[index]
                    ItemChoice(
                        category = category,
                        chosenItem = state.chosenOptions[category] ?: 0,
                        onItemChosen = { chosenIndex -> onOptionChosen(category, chosenIndex) }
                    )
                }
                item {
                    MyButton(
                        text = stringResource(R.string.customisation_proceed),
                        onClick = onProceedClicked
                    )
                }
            }
            MyGradient()
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
            modifier = Modifier.padding(4.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow {
            items(category.items.size) { index ->
                val option = category.items[index]
                val isSelected = index == chosenItem
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(option.iconUrl)
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = category.name,
                    modifier = Modifier
                        .padding(6.dp)
                        .size(88.dp)
                        .background(
                            if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                            else MaterialTheme.colorScheme.surface
                        )
                        .clickable {
                            onItemChosen(index)
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}