package com.example.drujite.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ProfileIcon: ImageVector
    get() {
        if (_ProfileIcon != null) {
            return _ProfileIcon!!
        }
        _ProfileIcon = ImageVector.Builder(
            name = "ProfileIcon",
            defaultWidth = 34.dp,
            defaultHeight = 34.dp,
            viewportWidth = 34f,
            viewportHeight = 34f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.15f
            ) {
                moveTo(22.667f, 21.25f)
                horizontalLineTo(11.333f)
                curveTo(8.204f, 21.25f, 5.667f, 23.787f, 5.667f, 26.917f)
                verticalLineTo(29.75f)
                horizontalLineTo(28.333f)
                verticalLineTo(26.917f)
                curveTo(28.333f, 23.787f, 25.796f, 21.25f, 22.667f, 21.25f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.15f
            ) {
                moveTo(17f, 15.583f)
                curveTo(20.129f, 15.583f, 22.667f, 13.046f, 22.667f, 9.917f)
                curveTo(22.667f, 6.787f, 20.129f, 4.25f, 17f, 4.25f)
                curveTo(13.87f, 4.25f, 11.333f, 6.787f, 11.333f, 9.917f)
                curveTo(11.333f, 13.046f, 13.87f, 15.583f, 17f, 15.583f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(22.667f, 21.25f)
                horizontalLineTo(11.333f)
                curveTo(8.204f, 21.25f, 5.667f, 23.787f, 5.667f, 26.917f)
                verticalLineTo(29.75f)
                horizontalLineTo(28.333f)
                verticalLineTo(26.917f)
                curveTo(28.333f, 23.787f, 25.796f, 21.25f, 22.667f, 21.25f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(17f, 15.583f)
                curveTo(20.129f, 15.583f, 22.667f, 13.046f, 22.667f, 9.917f)
                curveTo(22.667f, 6.787f, 20.129f, 4.25f, 17f, 4.25f)
                curveTo(13.87f, 4.25f, 11.333f, 6.787f, 11.333f, 9.917f)
                curveTo(11.333f, 13.046f, 13.87f, 15.583f, 17f, 15.583f)
                close()
            }
        }.build()

        return _ProfileIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ProfileIcon: ImageVector? = null
