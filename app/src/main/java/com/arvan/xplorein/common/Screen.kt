package com.arvan.xplorein.common

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Booking : Screen("booking")
    object Profile : Screen("profile")
}