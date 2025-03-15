package com.example.drujite.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.drujite.R
import com.example.domain.models.GoalModel
import com.example.drujite.presentation.MyTitle
import com.example.drujite.presentation.MyTitle2
import com.example.drujite.presentation.ShortenedTextBig
import io.github.composegears.valkyrie.BellIcon
import io.github.composegears.valkyrie.SmallProfileIcon

@Composable
fun HomeView() {
    MainState()
}

@Composable
fun MainState() {
    val clothingItems = List(3) { R.drawable.hair }
    val goals = remember {
        mutableStateOf(
            listOf(
                GoalModel("Найти артефакт Слытко", true),
                GoalModel(
                    "Узнать тайны директрисы Колдовстворца - Хозяйки Медной горы",
                    false
                ),
                GoalModel(
                    "Раскрыть тайну природы теней, пришедших в школу вслед за певцами теней",
                    false
                )
            )
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 24.dp, vertical = 32.dp)
            .padding(bottom = 32.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                imageVector = SmallProfileIcon,
                contentDescription = "Profile",
            )
            Image(
                imageVector = BellIcon,
                contentDescription = "Settings",
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
            ) {
                clothingItems.forEach {
                    ClothingItem(it)
                }

            }
            Image(
                painter = painterResource(id = R.drawable.character),
                contentDescription = "Character",
                modifier = Modifier
                    .size(180.dp, 256.dp)
            )
            Column(
                modifier = Modifier
            ) {
                clothingItems.forEach {
                    ClothingItem(it)
                }
            }

        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyTitle(text = "Мирон Арестов")
            MyTitle2(text = "Гранатовая ветвь")
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Квента",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                ElevatedButton(
                    modifier = Modifier.size(108.dp, 32.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.Black
                    ),
                    onClick = { }
                ) {
                    Text(
                        text = "Править",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }

            }
            ElevatedCard(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.Black,
                    disabledContentColor = Color.Black,
                    disabledContainerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                ShortenedTextBig(
                    text = "Мирон истинный уроженец Гранатовой ветви. Будучи выращенным вблизи вулканов, он с детства познавал дикую магию, подвергался изнуряющим тренировкам и был свидетелем...\n\n\n\n\n\n\n\nОчень длинное поле",
                    maxLines = 4
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Цели",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            goals.value.forEach {
                GoalItem(it) {

                }


            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    AppTheme { MainState() }
}

@Composable
fun ClothingItem(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Clothing",
        modifier = Modifier
            .padding(vertical = 12.dp)
            .size(64.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
    )
}

@Composable
fun GoalItem(goal: GoalModel, onGoalClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp).border(1.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(12.dp)),

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = goal.isCompleted,
                onCheckedChange = { },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    checkedColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = goal.goal,
            )
        }
    }
}