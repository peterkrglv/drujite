package com.example.drujite.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.AppTheme
import com.example.drujite.AboutSession.AboutSessionView
import com.example.drujite.ui.Timetable.TimeTableView
import com.example.drujite.ui.character.CharacterView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AboutSessionView()
            }
        }
    }
}