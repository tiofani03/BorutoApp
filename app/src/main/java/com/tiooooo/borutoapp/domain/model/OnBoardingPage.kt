package com.tiooooo.borutoapp.domain.model

import androidx.annotation.DrawableRes
import com.tiooooo.borutoapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
) {
    object First : OnBoardingPage(
        image = R.drawable.greetings,
        title = "Welcome to Boruto App",
        description = "Boruto App is an application that contains information about the characters in the Boruto series"
    )

    object Second : OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Explore the characters in the Boruto series"
    )

    object Third : OnBoardingPage(
        image = R.drawable.power,
        title = "Power",
        description = "See the power of each character in the Boruto series"
    )
}

