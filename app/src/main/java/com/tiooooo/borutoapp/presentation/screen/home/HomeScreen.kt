package com.tiooooo.borutoapp.presentation.screen.home

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.tiooooo.borutoapp.presentation.components.RatingWidget

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    Scaffold(
        modifier = Modifier,
        topBar = { HomeTopBar(onSearchClick = { }) },
    ) {
        RatingWidget(
            modifier = Modifier.padding(it),
            rating = 4.5
        )
    }
}
