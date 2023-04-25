package com.tiooooo.borutoapp.presentation.screen.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier,
        topBar = { HomeTopBar(onSearchClick = { }) },
    ) {}
}
