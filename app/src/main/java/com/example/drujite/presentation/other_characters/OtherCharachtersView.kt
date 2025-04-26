package com.example.drujite.presentation.other_characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.domain.models.CharacterModel
import com.example.drujite.R
import com.example.drujite.presentation.icons.ExpandArrowIcon
import com.example.drujite.presentation.my_composables.BottomSheetCharacter
import com.example.drujite.presentation.my_composables.LazyGridCharacters
import com.example.drujite.presentation.my_composables.LoadingScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun OtherCharactersView(
    viewModel: OtherCharactersViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()

    when (val state = viewState.value) {
        is OtherCharactersState.Initialization -> {
            LoadingScreen()
            viewModel.obtainEvent(OtherCharactersEvent.LoadData)
        }

        is OtherCharactersState.Main -> {
            MainState(
                state = state,
                onFilterCleared = { viewModel.obtainEvent(OtherCharactersEvent.ClearFilter) },
                onFilterByClan = { viewModel.obtainEvent(OtherCharactersEvent.FilterByClan(it)) }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainState(
    state: OtherCharactersState.Main,
    onFilterCleared: () -> Unit,
    onFilterByClan: (String) -> Unit
) {
    val allClans = stringResource(R.string.all_clans)
    val displayedCharacters = state.displayedCharacters
    val clans = listOf(allClans) + state.clans
    val showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val chosenCharacter = remember { mutableStateOf<CharacterModel?>(null) }
    val selectedClan = remember { mutableStateOf("Все кланы") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.End
    ) {
        CharacterChoiceMenu(
            clans = clans,
            selectedClan = selectedClan.value,
            onOptionSelected = {
                if (it == allClans) {
                    onFilterCleared()
                } else {
                    onFilterByClan(it)
                }
            }
        )
        LazyGridCharacters(
            characters = displayedCharacters,
            onCharacterClick = { character ->
                chosenCharacter.value = character
                showBottomSheet.value = true
            }
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterChoiceMenu(
    clans: List<String>,
    selectedClan: String,
    onOptionSelected: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selected = remember { mutableStateOf(selectedClan) }

    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value },
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier
                    .menuAnchor(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = selected.value,
                    fontSize = 17.sp
                )
                Icon(
                    imageVector = ExpandArrowIcon,
                    contentDescription = "Expand arrow",
                    modifier = Modifier.size(24.dp)
                )
            }
            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                containerColor = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp)
            ) {
                clans.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item, maxLines = 1) },
                        onClick = {
                            onOptionSelected(item)
                            expanded.value = false
                        },
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun OtherCharactersPreview() {
    AppTheme {
        OtherCharactersView()
    }
}
