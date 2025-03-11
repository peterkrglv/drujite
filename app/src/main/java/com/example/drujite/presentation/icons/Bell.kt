package io.github.composegears.valkyrie

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ValkyrieIcons.Bell: ImageVector
    get() {
        if (_Bell != null) {
            return _Bell!!
        }
        _Bell = ImageVector.Builder(
            name = "Bell",
            defaultWidth = 34.dp,
            defaultHeight = 34.dp,
            viewportWidth = 34f,
            viewportHeight = 34f
        ).apply {
            path(fill = SolidColor(Color(0xFF8E2F20))) {
                moveTo(21.25f, 25.5f)
                curveTo(21.25f, 26.058f, 21.14f, 26.611f, 20.926f, 27.126f)
                curveTo(20.713f, 27.642f, 20.4f, 28.111f, 20.005f, 28.505f)
                curveTo(19.61f, 28.9f, 19.142f, 29.213f, 18.626f, 29.427f)
                curveTo(18.111f, 29.64f, 17.558f, 29.75f, 17f, 29.75f)
                curveTo(16.442f, 29.75f, 15.889f, 29.64f, 15.373f, 29.427f)
                curveTo(14.858f, 29.213f, 14.389f, 28.9f, 13.995f, 28.505f)
                curveTo(13.6f, 28.111f, 13.287f, 27.642f, 13.073f, 27.126f)
                curveTo(12.86f, 26.611f, 12.75f, 26.058f, 12.75f, 25.5f)
                verticalLineTo(24.083f)
                horizontalLineTo(5.667f)
                curveTo(5.667f, 24.083f, 8.5f, 18.417f, 8.5f, 12.75f)
                curveTo(8.5f, 8.12f, 12.37f, 4.25f, 17f, 4.25f)
                curveTo(21.629f, 4.25f, 25.5f, 8.12f, 25.5f, 12.75f)
                curveTo(25.5f, 17f, 28.333f, 24.083f, 28.333f, 24.083f)
                horizontalLineTo(21.25f)
                verticalLineTo(25.5f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF8E2F20)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12.75f, 24.083f)
                verticalLineTo(25.5f)
                curveTo(12.75f, 26.058f, 12.86f, 26.611f, 13.073f, 27.126f)
                curveTo(13.287f, 27.642f, 13.6f, 28.111f, 13.995f, 28.505f)
                curveTo(14.389f, 28.9f, 14.858f, 29.213f, 15.373f, 29.427f)
                curveTo(15.889f, 29.64f, 16.442f, 29.75f, 17f, 29.75f)
                curveTo(17.558f, 29.75f, 18.111f, 29.64f, 18.626f, 29.427f)
                curveTo(19.142f, 29.213f, 19.61f, 28.9f, 20.005f, 28.505f)
                curveTo(20.4f, 28.111f, 20.713f, 27.642f, 20.926f, 27.126f)
                curveTo(21.14f, 26.611f, 21.25f, 26.058f, 21.25f, 25.5f)
                verticalLineTo(24.083f)
                moveTo(12.75f, 24.083f)
                horizontalLineTo(5.667f)
                curveTo(5.667f, 24.083f, 8.5f, 18.417f, 8.5f, 12.75f)
                curveTo(8.5f, 8.12f, 12.37f, 4.25f, 17f, 4.25f)
                curveTo(21.629f, 4.25f, 25.5f, 8.12f, 25.5f, 12.75f)
                curveTo(25.5f, 17f, 28.333f, 24.083f, 28.333f, 24.083f)
                horizontalLineTo(21.25f)
                moveTo(12.75f, 24.083f)
                horizontalLineTo(21.25f)
            }
        }.build()

        return _Bell!!
    }

@Suppress("ObjectPropertyName")
private var _Bell: ImageVector? = null
