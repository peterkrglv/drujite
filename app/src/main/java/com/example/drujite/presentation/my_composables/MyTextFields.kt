package com.example.drujite.presentation.my_composables

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp


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
            .width(340.dp)
            .padding(8.dp)
            .verticalScroll(scrollState),
        value = value,
        label = { Text(text = label) },
        isError = isError,
        shape = RoundedCornerShape(12.dp),
        onValueChange = onValueChange,
        minLines = 7,
        maxLines = 7
    )
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
        modifier = Modifier.width(324.dp),
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = 8.dp)
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
                    RoundedCornerShape(16.dp)
                )
                .padding(vertical = 8.dp),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(16.dp)
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