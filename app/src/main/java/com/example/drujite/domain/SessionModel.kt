package com.example.drujite.domain

import android.graphics.Bitmap

data class SessionModel(
    val name: String,
    val description: String,
    val dates: String,
    val nOfPlayers: Int,
    val image: Bitmap?
)