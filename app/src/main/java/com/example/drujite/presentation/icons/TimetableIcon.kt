package com.example.drujite.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val TimeTableIcon: ImageVector
    get() {
        if (_TimeTableIcon != null) {
            return _TimeTableIcon!!
        }
        _TimeTableIcon = ImageVector.Builder(
            name = "TimeTableIcon",
            defaultWidth = 34.dp,
            defaultHeight = 34.dp,
            viewportWidth = 34f,
            viewportHeight = 34f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.15f
            ) {
                moveTo(7.083f, 7.083f)
                curveTo(7.083f, 6.301f, 7.718f, 5.667f, 8.5f, 5.667f)
                horizontalLineTo(25.5f)
                curveTo(26.283f, 5.667f, 26.917f, 6.301f, 26.917f, 7.083f)
                verticalLineTo(26.917f)
                curveTo(26.917f, 27.699f, 26.283f, 28.333f, 25.5f, 28.333f)
                horizontalLineTo(8.5f)
                curveTo(7.718f, 28.333f, 7.083f, 27.699f, 7.083f, 26.917f)
                verticalLineTo(7.083f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(11.333f, 8.5f)
                horizontalLineTo(26.917f)
                moveTo(11.333f, 17f)
                horizontalLineTo(26.917f)
                moveTo(11.333f, 25.5f)
                horizontalLineTo(26.917f)
                moveTo(7.083f, 8.5f)
                verticalLineTo(8.514f)
                moveTo(7.083f, 17f)
                verticalLineTo(17.014f)
                moveTo(7.083f, 25.5f)
                verticalLineTo(25.514f)
            }
        }.build()

        return _TimeTableIcon!!
    }

@Suppress("ObjectPropertyName")
private var _TimeTableIcon: ImageVector? = null
