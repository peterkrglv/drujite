package com.example.drujite.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NewsIcon: ImageVector
    get() {
        if (_NewsIcon != null) {
            return _NewsIcon!!
        }
        _NewsIcon = ImageVector.Builder(
            name = "NewsIcon",
            defaultWidth = 30.dp,
            defaultHeight = 30.dp,
            viewportWidth = 30f,
            viewportHeight = 30f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.15f
            ) {
                moveTo(8.75f, 26.25f)
                verticalLineTo(20f)
                horizontalLineTo(5f)
                verticalLineTo(5f)
                horizontalLineTo(25f)
                verticalLineTo(20f)
                horizontalLineTo(15f)
                lineTo(8.75f, 26.25f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(8.75f, 26.25f)
                verticalLineTo(20f)
                horizontalLineTo(5f)
                verticalLineTo(5f)
                horizontalLineTo(25f)
                verticalLineTo(20f)
                horizontalLineTo(15f)
                lineTo(8.75f, 26.25f)
                close()
            }
        }.build()

        return _NewsIcon!!
    }

@Suppress("ObjectPropertyName")
private var _NewsIcon: ImageVector? = null
