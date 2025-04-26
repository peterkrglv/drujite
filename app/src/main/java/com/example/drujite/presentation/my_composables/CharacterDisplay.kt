package com.example.drujite.presentation.my_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.CharacterModel
import com.example.domain.models.SessionModel
import com.example.drujite.R

@Composable
fun CharacterCard(character: CharacterModel) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
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
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = character.clan,
            fontSize = 17.sp,
            maxLines = 1,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
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
            MyCard (
                modifier = Modifier.fillMaxWidth().padding(4.dp).clickable {
                    onCharacterClick(character)
                },
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                CharacterCard(character = character)
            }
        }
    }
}

@Composable
fun LazyGridSessions(
    sessions: List<SessionModel>,
    onSessionClick: (SessionModel) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(sessions) { session ->
            MyCard(
                modifier = Modifier.fillMaxWidth().padding(4.dp).clickable {
                    onSessionClick(session)
                },
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp), text = session.name,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = session.dates,
                        fontSize = 17.sp
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun MyComposablesPreview() {
    LazyGridSessions(
        sessions = listOf(
            SessionModel(
                id = 1,
                name = "Сессия 1",
                dates = "2023-10-01",
                description = "Описание сессии 1",
                imageUrl = ""
            ),
            SessionModel(
                id = 2,
                name = "Сессия 2",
                dates = "2023-10-02",
                description = "Описание сессии 2",
                imageUrl = ""
            ),
            SessionModel(
                id = 3,
                name = "Сессия 3",
                dates = "2023-10-03",
                description = "Описание сессии 3",
                imageUrl = ""
            ),
            SessionModel(
                id = 4,
                name = "Сессия 4",
                dates = "2023-10-04",
                description = "Описание сессии 4",
                imageUrl = ""
            )
        ),
        onSessionClick = {}

    )
}
