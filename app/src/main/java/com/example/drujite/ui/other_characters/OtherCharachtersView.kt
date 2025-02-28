package com.example.drujite.ui.other_characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.drujite.R
import com.example.drujite.domain.CharacterModel
import com.example.drujite.domain.getCharactersTest
import com.example.drujite.ui.icons.ExpandArrow


@Composable
fun OtherCharactersView() {
    MainState()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainState() {
    val characters = getCharactersTest()
    val clans = listOf("Все кланы") + characters.map { it.clan }.distinct()
    val showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val chosenCharacter = remember { mutableStateOf<CharacterModel?>(null) }
    val selectedClan = remember { mutableStateOf<String>("Все кланы") }
    val expanded = remember { mutableStateOf(false) }

    Scaffold() { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(contentPadding),
            horizontalAlignment = Alignment.End
        ) {
            CharacterChoiceMenu(
                clans = clans,
                selectedClan = selectedClan.value ?: "",
                onOptionSelected = { selectedClan.value = it }
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(characters.filter { character ->
                    selectedClan.value == "Все кланы" || character.clan == selectedClan.value
                }) { character ->
                    Box(
                        modifier = Modifier.clickable {
                            chosenCharacter.value = character
                            showBottomSheet.value = true
                        },
                        contentAlignment = Alignment.Center
                    ) {
                        CharacterCard(character = character)
                    }
                }
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterChoiceMenu(
    clans: List<String>,
    selectedClan: String,
    onOptionSelected: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedClan = remember { mutableStateOf(selectedClan) }

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
                    text = selectedClan.value,
                    fontSize = 17.sp
                )
                Icon(
                    imageVector = ExpandArrow,
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


@Composable
fun CharacterCard(character: CharacterModel) {
    Column(
        modifier = Modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.character),
            contentDescription = "Character image",
            modifier = Modifier
                .size(140.dp, 200.dp)
                .padding(4.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(4.dp), text = character.name,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = character.clan,
            fontSize = 17.sp
        )
    }
}

@Composable
fun BottomSheetCharacter(character: CharacterModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharacterCard(character = character)
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Описание",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = character.story,
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Отыгрывает",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = character.player,
                fontSize = 16.sp
            )
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
