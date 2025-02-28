package com.example.drujite.ui.about_session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.drujite.domain.SessionModel
import com.example.drujite.ui.MyTitle
import com.example.drujite.ui.MyTitle2

@Composable
fun AboutSessionView() {
    MainState()
}

@Composable
fun MainState() {
    val session = SessionModel(
        name = "Тайны Топей",
        description = "В школу магии Колдовстворец прибыл представитель нового движения магических охотников. Ведуны называют себя Яд волка. Колдун рассказал, что все Ветви – магические объединения магов, – пытаются попасть на территорию Виванитовой Ветви. Это самая мрачная территорий Славянского магического государства! Но им необходима помощь в борьбе с опасными существами – ветилами.\n" +
                "\n" +
                "Ученики славянской школы будут обучаться магии древних шаманов, прибывших из болот. Древние ритуалы и обряды ждут своего часа, а особо сильные зелья уже готовы к использованию.",
        dates = "01.06  - 12.06",
        nOfPlayers = 31
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                horizontal = 24.dp,
                vertical = 32.dp
            )
            .padding(bottom = 32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyTitle2(text = "Название смены")
            MyTitle(text = session.name)
            Text(modifier = Modifier.padding(vertical = 16.dp), text = session.dates)
        }
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = session.description,
            fontSize = 13.sp,
            textAlign = TextAlign.Justify
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Количество игроков:",
                fontSize = 17.sp
            )
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = session.nOfPlayers.toString(),
                fontSize = 17.sp
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PreviewAboutSessionView() {
    AppTheme {
        AboutSessionView()
    }
}
