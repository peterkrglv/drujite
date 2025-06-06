package com.example.drujite.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val HomeIcon: ImageVector
    get() {
        if (_HomeIcon != null) {
            return _HomeIcon!!
        }
        _HomeIcon = ImageVector.Builder(
            name = "HomeIcon",
            defaultWidth = 30.dp,
            defaultHeight = 30.dp,
            viewportWidth = 30f,
            viewportHeight = 30f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.15f
            ) {
                moveTo(15f, 3.75f)
                curveTo(15f, 9.963f, 20.037f, 15f, 26.25f, 15f)
                curveTo(20.037f, 15f, 15f, 20.037f, 15f, 26.25f)
                curveTo(15f, 20.037f, 9.963f, 15f, 3.75f, 15f)
                curveTo(9.963f, 15f, 15f, 9.963f, 15f, 3.75f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15f, 3.75f)
                curveTo(15f, 9.963f, 20.037f, 15f, 26.25f, 15f)
                curveTo(20.037f, 15f, 15f, 20.037f, 15f, 26.25f)
                curveTo(15f, 20.037f, 9.963f, 15f, 3.75f, 15f)
                curveTo(9.963f, 15f, 15f, 9.963f, 15f, 3.75f)
                close()
            }
        }.build()

        return _HomeIcon!!
    }

@Suppress("ObjectPropertyName")
private var _HomeIcon: ImageVector? = null
