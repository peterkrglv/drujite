package com.example.drujite.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val AboutIcon: ImageVector
    get() {
        if (_AboutIcon != null) {
            return _AboutIcon!!
        }
        _AboutIcon = ImageVector.Builder(
            name = "AboutIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color(0xFFE3E3E3))) {
                moveTo(240f, 560f)
                horizontalLineToRelative(320f)
                verticalLineToRelative(-80f)
                lineTo(240f, 480f)
                verticalLineToRelative(80f)
                close()
                moveTo(240f, 440f)
                horizontalLineToRelative(480f)
                verticalLineToRelative(-80f)
                lineTo(240f, 360f)
                verticalLineToRelative(80f)
                close()
                moveTo(240f, 320f)
                horizontalLineToRelative(480f)
                verticalLineToRelative(-80f)
                lineTo(240f, 240f)
                verticalLineToRelative(80f)
                close()
                moveTo(80f, 880f)
                verticalLineToRelative(-720f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(160f, 80f)
                horizontalLineToRelative(640f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(880f, 160f)
                verticalLineToRelative(480f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(800f, 720f)
                lineTo(240f, 720f)
                lineTo(80f, 880f)
                close()
                moveTo(206f, 640f)
                horizontalLineToRelative(594f)
                verticalLineToRelative(-480f)
                lineTo(160f, 160f)
                verticalLineToRelative(525f)
                lineToRelative(46f, -45f)
                close()
                moveTo(160f, 640f)
                verticalLineToRelative(-480f)
                verticalLineToRelative(480f)
                close()
            }
        }.build()

        return _AboutIcon!!
    }

@Suppress("ObjectPropertyName")
private var _AboutIcon: ImageVector? = null
