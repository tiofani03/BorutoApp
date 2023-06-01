package com.tiooooo.borutoapp.presentation.screen.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tiooooo.borutoapp.utils.Constants
import com.tiooooo.borutoapp.utils.PaletteGenerator
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val selectedHero by detailViewModel.selectedHero.collectAsState()
    val colorPalette by detailViewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        selectedHero?.let {
            DetailContent(
                navController = navController,
                selectedHero = it,
                colors = colorPalette,
            )
        }
    } else {
        detailViewModel.generateColorPalette()
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        detailViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = PaletteGenerator.convertImageUrlToBitmap(
                        imageUrl = "${Constants.BASE_URl}${selectedHero?.image}",
                        context = context,
                    )
                    if (bitmap != null) {
                        detailViewModel.setColoPalette(
                            color = PaletteGenerator.extractColorFromBitmap(bitmap)
                        )
                    }
                }
            }
        }
    }
}
