package com.arvan.xplorein.ui.presentation.onboarding

import androidx.annotation.DrawableRes
import com.arvan.xplorein.R


data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Explore Your Dream Destination:",
        description = "Find various interesting tourist options throughout Indonesia easily and practically.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Discover Delicious \n" +
                "Culinary: ",
        description = "Explore a variety of regional culinary specialties that must be tried in every tourist destination.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Traveling is More\n" +
                "Exciting",
        description = "Experience a more enjoyable and memorable travel experience with new friends.",
        image = R.drawable.onboarding3
    )
)