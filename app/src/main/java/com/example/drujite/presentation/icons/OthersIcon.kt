package com.example.drujite.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val OthersIcon: ImageVector
    get() {
        if (_OthersIcon != null) {
            return _OthersIcon!!
        }
        _OthersIcon = ImageVector.Builder(
            name = "OthersIcon",
            defaultWidth = 28.dp,
            defaultHeight = 28.dp,
            viewportWidth = 28f,
            viewportHeight = 28f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.15f
            ) {
                moveTo(15.167f, 17.5f)
                horizontalLineTo(5.833f)
                curveTo(3.256f, 17.5f, 1.166f, 19.589f, 1.166f, 22.167f)
                verticalLineTo(24.5f)
                horizontalLineTo(19.833f)
                verticalLineTo(22.167f)
                curveTo(19.833f, 19.589f, 17.744f, 17.5f, 15.167f, 17.5f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.15f
            ) {
                moveTo(10.5f, 12.833f)
                curveTo(13.077f, 12.833f, 15.167f, 10.744f, 15.167f, 8.167f)
                curveTo(15.167f, 5.589f, 13.077f, 3.5f, 10.5f, 3.5f)
                curveTo(7.923f, 3.5f, 5.833f, 5.589f, 5.833f, 8.167f)
                curveTo(5.833f, 10.744f, 7.923f, 12.833f, 10.5f, 12.833f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(22.167f, 17.5f)
                curveTo(24.744f, 17.5f, 26.833f, 19.589f, 26.833f, 22.167f)
                verticalLineTo(24.5f)
                horizontalLineTo(24.5f)
                moveTo(18.667f, 12.686f)
                curveTo(20.679f, 12.168f, 22.167f, 10.341f, 22.167f, 8.167f)
                curveTo(22.167f, 5.992f, 20.679f, 4.165f, 18.667f, 3.647f)
                moveTo(15.167f, 8.167f)
                curveTo(15.167f, 10.744f, 13.077f, 12.833f, 10.5f, 12.833f)
                curveTo(7.923f, 12.833f, 5.833f, 10.744f, 5.833f, 8.167f)
                curveTo(5.833f, 5.589f, 7.923f, 3.5f, 10.5f, 3.5f)
                curveTo(13.077f, 3.5f, 15.167f, 5.589f, 15.167f, 8.167f)
                close()
                moveTo(5.833f, 17.5f)
                horizontalLineTo(15.167f)
                curveTo(17.744f, 17.5f, 19.833f, 19.589f, 19.833f, 22.167f)
                verticalLineTo(24.5f)
                horizontalLineTo(1.166f)
                verticalLineTo(22.167f)
                curveTo(1.166f, 19.589f, 3.256f, 17.5f, 5.833f, 17.5f)
                close()
            }
        }.build()

        return _OthersIcon!!
    }

@Suppress("ObjectPropertyName")
private var _OthersIcon: ImageVector? = null
