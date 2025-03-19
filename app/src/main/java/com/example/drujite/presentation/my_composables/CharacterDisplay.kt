package com.example.drujite.presentation.my_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.CharacterModel
import com.example.drujite.R

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

@Composable
fun LazyGridCharacters(
    characters: List<CharacterModel>,
    onCharacterClick: (CharacterModel) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(characters) { character ->
            Box(
                modifier = Modifier.clickable {
                    onCharacterClick(character)
                },
                contentAlignment = Alignment.Center
            ) {
                CharacterCard(character = character)
            }
        }
    }
}

