package com.example.drujite.presentation.my_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.drujite.R
import com.example.drujite.presentation.icons.ArrowIcon
import com.example.drujite.presentation.icons.Clear
import com.example.drujite.presentation.signup.Gender

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
        Text(text = stringResource(R.string.gender_choice))
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

@Composable
fun MyCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerLowest,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(8.dp)
    val outlinePadding = 2.dp

    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(shape)
                .border(10.dp, MaterialTheme.colorScheme.onBackground, shape)
                .padding(contentPadding)
        )
        Box(
            modifier = Modifier
                .padding(bottom = outlinePadding)
                .padding(end = outlinePadding)
                .clip(shape)
                .background(containerColor)
                .border(1.dp, MaterialTheme.colorScheme.onBackground, shape)
                .padding(contentPadding)
        ) {
            content()
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyCard(modifier = Modifier.padding(4.dp)) {
                Text(
                    modifier = Modifier.size(100.dp),
                    text = "Hello"
                )
            }
            MyCard(
                modifier = Modifier,
                containerColor = Color.Gray
            ) {
                Icon(
                    imageVector = ArrowIcon,
                    contentDescription = "Arrow",
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownSearchBar(
    searchQuery: String,
    onSearch: (String) -> Unit,
    queryHistory: List<String> = emptyList(),
    onQueryChanged: (String) -> Unit,
    onClearSearchHistoryClicked: () -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val history = queryHistory
        .filter { it.contains(searchQuery, ignoreCase = true) && it != searchQuery }
        .take(5)
    ExposedDropdownMenuBox(
        modifier = Modifier.fillMaxWidth(),
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .menuAnchor(),
            value = searchQuery,
            onValueChange = {
                onQueryChanged(it)
                expanded.value = true
            },
            maxLines = 1,
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                MyCard(
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable {
                            onSearch(searchQuery)
                            expanded.value = false
                        },
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentPadding = PaddingValues(8.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .aspectRatio(1f),
                        imageVector = ArrowIcon,
                        contentDescription = "Search",
                        tint = Color(0xFFC8C8C8)
                    )
                }
            },
            leadingIcon = {
                MyCard(
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable {
                            onQueryChanged("")
                            expanded.value = true
                        },
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentPadding = PaddingValues(8.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .aspectRatio(1f),
                        imageVector = Clear,
                        contentDescription = "Clear",
                        tint = Color(0xFFC8C8C8)
                    )
                }
            }
        )
        if (history.isNotEmpty()) {
            ExposedDropdownMenu(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                history.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            onSearch(item)
                            expanded.value = false
                        }
                    )
                }
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Очистить историю",
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    onClick = {
                        onQueryChanged("")
                        onClearSearchHistoryClicked()
                        expanded.value = false
                    }
                )
            }
        }
    }
}