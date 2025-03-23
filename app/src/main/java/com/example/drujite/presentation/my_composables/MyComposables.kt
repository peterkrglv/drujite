package com.example.drujite.presentation.my_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
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
    modifier: Modifier,
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
                .padding(4.dp)
        )
        Box(
            modifier = Modifier
                .padding(bottom = outlinePadding)
                .padding(end = outlinePadding)
                .clip(shape)
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .border(1.dp, MaterialTheme.colorScheme.onBackground, shape)
                .padding(4.dp)
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

        }
    }
}