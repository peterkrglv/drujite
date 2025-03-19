package com.example.drujite.presentation.my_composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyButton(
    text: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .padding(32.dp)
            .size(280.dp, 60.dp),
        onClick = onClick,
    ) {
        Text(
            fontSize = 20.sp,
            text = text
        )
    }
}

@Composable
fun MyButtonSmall(
    text: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = Modifier
            .padding(16.dp)
            .size(160.dp, 45.dp),
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.Black
        )
    ) {
        Text(
            fontSize = 18.sp,
            text = text
        )
    }
}

@Composable
fun MyTextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(modifier = Modifier, onClick = onClick) {
        Text(
            modifier = Modifier,
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
    }
}

@Composable
fun TextButtonNavigation(
    text: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MyText(text = text)
        MyTextButton(text = buttonText, onClick = onClick)
    }
}