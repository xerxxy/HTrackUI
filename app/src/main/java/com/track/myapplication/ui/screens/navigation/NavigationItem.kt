package com.track.myapplication.ui.screens.navigation

import androidx.annotation.DrawableRes

data class NavigationItem(
    val title: String,
    @DrawableRes val selectedIcon: Int,
    val badgeCount: Int? = null,
    val route: String
)

