package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.drujite.R

val Monsterrat = FontFamily(
    Font(R.font.montserrat_medium, FontWeight.Medium)
)

val Raleway = FontFamily(
    Font(R.font.raleway_medium, FontWeight.Medium),
    Font(R.font.raleway_bold, FontWeight.Bold)
)

val Inter = FontFamily(
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_bold, FontWeight.Bold)
)

val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Inter,
    ),
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = Inter,
        fontSize = 13.sp

    ),
    titleLarge = TextStyle(
        fontFamily = Raleway,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = TextStyle(
        fontFamily = Raleway,
        fontSize = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Monsterrat,
        fontSize = 20.sp
    ),
//    labelSmall = TextStyle(
//        fontFamily = Inter,
//        fontSize = 12.sp
//    ),
)
