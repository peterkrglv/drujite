package com.example.drujite.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ExpandArrowIcon: ImageVector
    get() {
        if (_ExpandArrow != null) {
            return _ExpandArrow!!
        }
        _ExpandArrow = ImageVector.Builder(
            name = "ExpandArrow",
            defaultWidth = 22.dp,
            defaultHeight = 12.dp,
            viewportWidth = 22f,
            viewportHeight = 12f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 1f
            ) {
                moveTo(1f, 1f)
                lineTo(11f, 11f)
                lineTo(21f, 1f)
            }
        }.build()

        return _ExpandArrow!!
    }

@Suppress("ObjectPropertyName")
private var _ExpandArrow: ImageVector? = null
