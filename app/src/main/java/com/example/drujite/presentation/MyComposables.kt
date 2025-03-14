package com.example.drujite.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drujite.presentation.signup.Gender

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
        modifier = Modifier.padding(vertical = 4.dp),
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
fun ShortenedTextBig(text: String, maxLines: Int) {
    val isTextOverFlow = remember { mutableStateOf(false) }
    val isTextShortened = remember { mutableStateOf(true) }
    val lines = remember { mutableStateOf(maxLines) }
    val textButtonText = remember { mutableStateOf("Подробнее") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
            )
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
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
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

@Composable
fun MyTextField(
    value: String,
    label: String,
    errorText: String?,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .width(340.dp)
            .padding(8.dp),
        value = value,
        label = { Text(text = label) },
        isError = errorText != null,
        shape = RoundedCornerShape(12.dp),
        onValueChange = onValueChange,
        singleLine = true,
        supportingText = { if (errorText != null) Text(errorText) }
    )
}

@Composable
fun MyPasswordField(
    value: String,
    label: String,
    errorText: String?,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .width(340.dp)
            .padding(8.dp),
        value = value,
        label = { Text(text = label) },
        isError = errorText != null,
        shape = RoundedCornerShape(12.dp),
        onValueChange = onValueChange,
        singleLine = true,
        supportingText = { if (errorText != null) Text(errorText) },
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun MyTextFieldWhite(
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
        singleLine = true,
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
        )
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
fun GenderChoice(
    onGenderClick: (Gender) -> Unit,
    gender: Gender
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
                selected = (gender == Gender.MALE),
                onClick = { onGenderClick(Gender.MALE) }
            )
            Text(text = Gender.MALE.value)
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = (gender == Gender.FEMALE),
                onClick = { onGenderClick(Gender.FEMALE) }
            )
            Text(text = Gender.FEMALE.value)
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
        modifier = Modifier.width(340.dp),
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
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
            modifier = Modifier
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(12.dp)
                )
                .padding(8.dp),
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
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyTextField(value = ",le", label = "f,wkem", errorText = "", onValueChange = {})
            DropdownTextField(
                label = "",
                options = emptyList(),
                selected = "",
                onOptionSelected = {})
        }
    }
}