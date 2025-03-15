package com.example.drujite.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.drujite.presentation.MyTitle

@Composable
fun ProfileView() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MyTitle("Здесь будет профиль")
    }
}