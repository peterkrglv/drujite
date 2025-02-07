package com.example.drujite.ui

import Creation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.AppTheme
import com.example.drujite.ui.character_transfer.Transfer
import com.example.drujite.ui.greeting.Greeting
import com.example.drujite.ui.login.Login
import com.example.drujite.ui.session_selection.Session
import com.example.drujite.ui.signup.Signup

class MainView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Transfer()
            }
        }
    }
}