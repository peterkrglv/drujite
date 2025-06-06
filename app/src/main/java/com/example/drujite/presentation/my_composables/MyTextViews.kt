package com.example.drujite.presentation.my_composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AppTypography
import com.example.ui.theme.Monsterrat
import com.example.ui.theme.Raleway

@Composable
fun MyTitle(
    text: String
) {
    Text(
        modifier = Modifier.padding(4.dp),
        textAlign = TextAlign.Center,
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        fontFamily = AppTypography.titleLarge.fontFamily
    )
}

@Composable
fun MyTitle2(
    text: String
) {
    Text(
        modifier = Modifier.padding(60.dp, 4.dp),
        textAlign = TextAlign.Center,
        text = text,
        fontSize = 16.sp,
        fontFamily = AppTypography.titleSmall.fontFamily
    )
}

@Composable
fun GreetingText1(
    text: String
) {
    Text(
        modifier = Modifier.padding(vertical = 4.dp),
        textAlign = TextAlign.Center,
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        fontFamily = Raleway
    )
}

@Composable
fun GreetingText2(
    text: String
) {
    Text(
        modifier = Modifier
            .width(300.dp)
            .padding(vertical = 4.dp),
        text = text,
        fontSize = 17.sp,
        fontFamily = AppTypography.titleSmall.fontFamily,
    )
}

@Composable
fun MyText(
    text: String
) {
    Text(
        modifier = Modifier.padding(vertical = 4.dp),
        textAlign = TextAlign.Center,
        text = text,
        fontSize = 16.sp
    )
}

@Composable
fun ShortenedTextBig(text: String, maxLines: Int, fontFamily: FontFamily = Monsterrat) {
    val isTextOverFlow = remember { mutableStateOf(false) }
    val isTextShortened = remember { mutableStateOf(true) }
    val lines = remember { mutableStateOf(maxLines) }
    val textButtonText = remember { mutableStateOf("Подробнее") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier,
            text = text,
            fontSize = 16.sp,
            maxLines = lines.value,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.didOverflowHeight) {
                    isTextOverFlow.value = true
                }
            },
            style = TextStyle(
                textAlign = TextAlign.Justify
            ),
            fontFamily = fontFamily
        )
        if (isTextOverFlow.value) {
            textButtonText.value = if (isTextShortened.value) "Раскрыть" else "Скрыть"
            Text(
                modifier = Modifier
                    .padding(0.dp)
                    .clickable {
                        isTextShortened.value = !isTextShortened.value
                        lines.value = if (isTextShortened.value) maxLines else Int.MAX_VALUE
                    },
                text = textButtonText.value,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = fontFamily
            )
        }
    }
}

@Composable
fun ShortenedText(text: String, maxLines: Int, fontFamily: FontFamily = Monsterrat) {
    val isTextOverFlow = remember { mutableStateOf(false) }
    val isTextShortened = remember { mutableStateOf(true) }
    val lines = remember { mutableStateOf(maxLines) }
    val textButtonText = remember { mutableStateOf("Подробнее") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier,
            text = text,
            fontSize = 13.sp,
            maxLines = lines.value,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.didOverflowHeight) {
                    isTextOverFlow.value = true
                }
            },
            style = TextStyle(
                textAlign = TextAlign.Justify
            ),
            fontFamily = fontFamily
        )
        if (isTextOverFlow.value) {
            textButtonText.value = if (isTextShortened.value) "Раскрыть" else "Скрыть"
            Text(
                modifier = Modifier
                    .padding(0.dp)
                    .clickable {
                        isTextShortened.value = !isTextShortened.value
                        lines.value = if (isTextShortened.value) maxLines else Int.MAX_VALUE
                    },
                text = textButtonText.value,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                fontFamily = fontFamily
            )
        }
    }
}

@Composable
fun MySmallText(
    text: String
) {
    Text(
        modifier = Modifier.padding(16.dp, 8.dp),
        textAlign = TextAlign.Center,
        text = text,
        fontSize = 13.sp
    )
}