package com.example.drujite.ui

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyTitle(
    text: String
) {
    Text(
        modifier = Modifier.padding(4.dp),
        textAlign = TextAlign.Center,
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
        textAlign = TextAlign.Center,
        text = text,
        fontSize = 16.sp
    )
}

@Composable
fun GreetingText1(
    text: String
) {
    Text(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
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
            .padding(vertical = 4.dp),
        text = text,
        fontSize = 17.sp
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
        singleLine = true
    )
}

@Composable
fun MyExpandedTextField(
    value: String,
    label: String,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    OutlinedTextField(
        modifier = Modifier
            .size(340.dp, 170.dp)
            .padding(8.dp)
            .verticalScroll(scrollState),
        value = value,
        label = { Text(text = label) },
        isError = isError,
        shape = RoundedCornerShape(12.dp),
        onValueChange = onValueChange,
        maxLines = 5
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

@Composable
fun TextButtonNavigation(
    text: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 64.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MyText(text = text)
        MyTextButton(text = buttonText, onClick = onClick)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownTextField(
    label: String,
    options: List<String>,
    selected: String,
    onOptionSelected: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedState = remember { mutableStateOf(selected) }
    val isError = remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(340.dp)
                .padding(8.dp)
                .clickable {
                    Log.d("DropdownTextField", "Clicked")
                    expanded.value = true
                }
                .menuAnchor(),
            value = selectedState.value,
            label = { Text(text = label) },
            placeholder = { Text(modifier = Modifier.fillMaxWidth(), text = label) },
            isError = isError.value,
            shape = RoundedCornerShape(12.dp),
            onValueChange = { },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
            readOnly = true,
        )
        ExposedDropdownMenu(
            modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp)),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(12.dp)
        ) {
            options.forEach { item ->

                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onOptionSelected(item)
                        selectedState.value = item
                        expanded.value = false
                    }
                )
                //Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
    MaterialTheme {
    }
}