package com.example.drujite.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color(0xFFE3E3E3))) {
                moveTo(200f, 800f)
                verticalLineToRelative(-366f)
                lineTo(88f, 520f)
                lineToRelative(-48f, -64f)
                lineToRelative(440f, -336f)
                lineToRelative(160f, 122f)
                verticalLineToRelative(-82f)
                horizontalLineToRelative(120f)
                verticalLineToRelative(174f)
                lineToRelative(160f, 122f)
                lineToRelative(-48f, 64f)
                lineToRelative(-112f, -86f)
                verticalLineToRelative(366f)
                lineTo(520f, 800f)
                verticalLineToRelative(-240f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(240f)
                lineTo(200f, 800f)
                close()
                moveTo(280f, 720f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-240f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(240f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-347f)
                lineTo(480f, 221f)
                lineTo(280f, 373f)
                verticalLineToRelative(347f)
                close()
                moveTo(400f, 401f)
                horizontalLineToRelative(160f)
                quadToRelative(0f, -32f, -24f, -52.5f)
                reflectiveQuadTo(480f, 328f)
                quadToRelative(-32f, 0f, -56f, 20.5f)
                reflectiveQuadTo(400f, 401f)
                close()
                moveTo(360f, 720f)
                verticalLineToRelative(-240f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(240f)
                verticalLineToRelative(-240f)
                lineTo(360f, 480f)
                verticalLineToRelative(240f)
                close()
            }
        }.build()

        return _HomeIcon!!
    }

@Suppress("ObjectPropertyName")
private var _HomeIcon: ImageVector? = null
