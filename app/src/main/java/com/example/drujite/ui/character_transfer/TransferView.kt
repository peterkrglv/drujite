package com.example.drujite.ui.character_transfer

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.drujite.ui.DropdownTextField
import com.example.drujite.ui.MyButton
import com.example.drujite.ui.MyExpandedTextField
import com.example.drujite.ui.MySmallText
import com.example.drujite.ui.MyTitle
import com.example.drujite.ui.MyTitle2
import com.example.drujite.ui.TextButtonNavigation

@Composable
fun TransferView() {
    MainState()
}

@Composable
fun MainState() {

    val characterName = remember { mutableStateOf("") }
    val reason = remember { mutableStateOf("") }
    val characters = listOf("Персонаж 1", "Персонаж 2", "Персонаж 3", "Персонаж 4", "Персонаж 5", "Персонаж 6", "Персонаж 7", "Персонаж 8", "Персонаж 9", "Персонаж 10", "Персонаж 11")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            MyTitle(text = "Перенос")
            MyTitle2(text = "Расскажи кураторам, почему ты считаешь, что участие этого персонажа в событиях смены оправданно")
            Spacer(modifier = Modifier.height(32.dp))
            DropdownTextField(
                label = "Выбери персонажа",
                options = characters,
                selected = "",
            ) {
                characterName.value = it
            }
            MyExpandedTextField(
                value = reason.value,
                label = "Причина",
                isError = false,
            ) {
                reason.value = it
            }
            MySmallText(text = "Важно: если временные промежутки этой смены и смены, с которой ты хочешь перенести персонажа, не совпадают или есть другие нюансы, куратор может отказать в переносе персонажа в целях сохранения логики игровых миров.")
            MyButton(text = "Дальше", onClick = {})
        }
        TextButtonNavigation(
            text = "Передумал?)",
            buttonText = "Назад",
            onClick = {}
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun TransferPreview() {
    AppTheme {
        TransferView()
    }
}