package com.example.drujite.ui.character_customisation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.drujite.R
import com.example.drujite.ui.MyButton
import com.example.drujite.ui.timetable.MainView

@Composable
fun CustomisationView(
    navController: NavController,
) {
    MainState()
}

@Composable
fun MainState() {
    val faceImages = List(10) { R.drawable.character }
    val chosenFace = remember { mutableStateOf(0) }
    val hairImages = List(10) { R.drawable.character }
    val chosenHair = remember { mutableStateOf(0) }
    val browImages = List(10) { R.drawable.character }
    val chosenBrow = remember { mutableStateOf(0) }
    val eyesImages = List(10) { R.drawable.character }
    val chosenEyes = remember { mutableStateOf(0) }
    val mouthImages = List(10) { R.drawable.character }
    val chosenMouth = remember { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 48.dp, horizontal = 16.dp),
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

        items(5) {
            when (it) {
                0 -> ItemChoice(
                    "Форма лица",
                    faceImages,
                    chosenFace.value,
                    onItemChosen = { chosenFace.value = it })

                1 -> ItemChoice(
                    "Прическа",
                    hairImages,
                    chosenHair.value,
                    onItemChosen = { chosenHair.value = it })

                2 -> ItemChoice(
                    "Брови",
                    browImages,
                    chosenBrow.value,
                    onItemChosen = { chosenBrow.value = it })

                3 -> ItemChoice(
                    "Глаза",
                    eyesImages,
                    chosenEyes.value,
                    onItemChosen = { chosenEyes.value = it })

                4 -> ItemChoice(
                    "Рот",
                    mouthImages,
                    chosenMouth.value,
                    onItemChosen = { chosenMouth.value = it })
            }
        }

    }
}


@Composable
fun ItemChoice(title: String, items: List<Int>, chosenItem: Int, onItemChosen: (Int) -> Unit) {
    val chosenItem = remember { mutableStateOf(chosenItem) }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow {
            items(items.size) { index ->
                Image(
                    painter = painterResource(id = items[index]),
                    contentDescription = "Character",
                    modifier = Modifier
                        .padding(6.dp)
                        .size(88.dp)
                        .clickable {
                            chosenItem.value = index
                            onItemChosen(index)
                        }
                )
            }
            item {
                MyButton(text = "Закончить", onClick = {})
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CustomisationPreview() {
    AppTheme {
        MainState()
    }
}