package com.raul.myapplication.ui.screens.navigation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    @DrawableRes val selectedIcon: Int,
    val badgeCount: Int? = null,
    val route: String
)

