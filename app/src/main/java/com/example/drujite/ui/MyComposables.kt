package com.example.drujite.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyTitle(
    text: String
) {
    Text(
        modifier = Modifier.padding(40.dp, 4.dp),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    )
}

@Composable
fun MyTitle2(
    text: String
) {
    Text(
        modifier = Modifier.padding(60.dp, 4.dp),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        text = text,
        fontSize = 16.sp
    )
}

@Composable
fun GreetingText1(
    text: String
) {
    Text(
        modifier = Modifier.padding(0.dp, 4.dp),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    )
}

@Composable
fun GreetingText2(
    text: String
) {
    Text(
        modifier = Modifier
            .width(300.dp)
            .padding(0.dp, 4.dp),
        text = text,
        fontSize = 17.sp
    )
}

@Composable
fun MyText(
    text: String
) {
    Text(
        modifier = Modifier,
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        text = text,
        fontSize = 16.sp
    )
}

@Composable
fun MyTextSmall(
    text: String
) {
    Text(
        modifier = Modifier,
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        text = text,
        fontSize = 13.sp
    )
}

@Composable
fun MyTextField(
    value: String,
    label: String,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .width(340.dp)
            .padding(8.dp),
        value = value,
        label = { Text(text = label) },
        isError = isError,
        shape = RoundedCornerShape(12.dp),
        onValueChange = onValueChange,
    )
}

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
    OutlinedButton(
        modifier = Modifier
            .padding(16.dp)
            .size(160.dp, 45.dp),
        onClick = onClick,
        colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
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
fun GenderChoice(
    onMClick: () -> Unit,
    onFClick: () -> Unit,
    gender: String
) {
    Row(
        modifier = Modifier.width(300.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Твой пол:")
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = (gender == "M"),
                onClick = onMClick
            )
            Text(text = "М")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = (gender == "F"),
                onClick = onFClick
            )
            Text(text = "Ж")
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
    MaterialTheme {
        GenderChoice(
            onMClick = {},
            onFClick = {},
            gender = "M"
        )
    }
}