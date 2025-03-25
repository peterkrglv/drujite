package com.example.drujite.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ArrowIcon: ImageVector
    get() {
        if (_Arrow != null) {
            return _Arrow!!
        }
        _Arrow = ImageVector.Builder(
            name = "Arrow",
            defaultWidth = 18.dp,
            defaultHeight = 14.dp,
            viewportWidth = 18f,
            viewportHeight = 14f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFC8C8C8)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(17f, 7f)
                lineTo(1f, 7f)
                moveTo(17f, 7f)
                lineTo(11f, 13f)
                moveTo(17f, 7f)
                lineTo(11f, 1f)
            }
        }.build()

        return _Arrow!!
    }

@Suppress("ObjectPropertyName")
private var _Arrow: ImageVector? = null
