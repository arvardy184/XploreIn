package com.arvan.xplorein.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
//
//data class BottomNavItem(
//
//    val name: String,
//    val route: String,
//    val icon: ImageVector
//)

sealed class BottomNavItem(

    val name: String,
    val route: String,
    val icon: ImageVector
) {
    object Home: BottomNavItem(
        name="Home",
        route= Screen.Home.route,
        icon = Icons.Default.Home
    )

    object Booking : BottomNavItem(
       name = "Booking",
       route = Screen.Booking.route,
       icon = Icons.Default.Description
    )

    object Profile : BottomNavItem(
        name = "Profile",
        route = Screen.Profile.route,
        icon = Icons.Default.Person
    )
}