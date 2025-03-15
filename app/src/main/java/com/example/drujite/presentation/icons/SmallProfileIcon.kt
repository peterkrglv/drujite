package io.github.composegears.valkyrie

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SmallProfileIcon: ImageVector
    get() {
        if (_Profile != null) {
            return _Profile!!
        }
        _Profile = ImageVector.Builder(
            name = "Profile",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(fill = SolidColor(Color(0xFF8E2F20))) {
                moveTo(21.333f, 20f)
                horizontalLineTo(10.667f)
                curveTo(7.721f, 20f, 5.333f, 22.388f, 5.333f, 25.333f)
                verticalLineTo(28f)
                horizontalLineTo(26.667f)
                verticalLineTo(25.333f)
                curveTo(26.667f, 22.388f, 24.279f, 20f, 21.333f, 20f)
                close()
            }
            path(fill = SolidColor(Color(0xFF8E2F20))) {
                moveTo(16f, 14.667f)
                curveTo(18.946f, 14.667f, 21.333f, 12.279f, 21.333f, 9.333f)
                curveTo(21.333f, 6.388f, 18.946f, 4f, 16f, 4f)
                curveTo(13.055f, 4f, 10.667f, 6.388f, 10.667f, 9.333f)
                curveTo(10.667f, 12.279f, 13.055f, 14.667f, 16f, 14.667f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF8E2F20)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(21.333f, 20f)
                horizontalLineTo(10.667f)
                curveTo(7.721f, 20f, 5.333f, 22.388f, 5.333f, 25.333f)
                verticalLineTo(28f)
                horizontalLineTo(26.667f)
                verticalLineTo(25.333f)
                curveTo(26.667f, 22.388f, 24.279f, 20f, 21.333f, 20f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF8E2F20)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16f, 14.667f)
                curveTo(18.946f, 14.667f, 21.333f, 12.279f, 21.333f, 9.333f)
                curveTo(21.333f, 6.388f, 18.946f, 4f, 16f, 4f)
                curveTo(13.055f, 4f, 10.667f, 6.388f, 10.667f, 9.333f)
                curveTo(10.667f, 12.279f, 13.055f, 14.667f, 16f, 14.667f)
                close()
            }
        }.build()

        return _Profile!!
    }

@Suppress("ObjectPropertyName")
private var _Profile: ImageVector? = null
