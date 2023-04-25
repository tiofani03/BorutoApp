package com.tiooooo.borutoapp.presentation.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tiooooo.borutoapp.R
import com.tiooooo.borutoapp.navigation.Screen
import com.tiooooo.borutoapp.ui.theme.Purple500
import com.tiooooo.borutoapp.ui.theme.Purple700

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()
    val rotateDegrees = remember { Animatable(0f) }
    LaunchedEffect(key1 = true) {
        rotateDegrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200,
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted) {
            navController.navigate(Screen.Home.route)
        } else {
            navController.navigate(Screen.Welcome.route)
        }

    }
    Splash(rotateDegrees.value)
}

@Composable
fun Splash(rotateDegrees: Float) {
    val logoResId = R.drawable.ic_logo
    val logoContentDesc = stringResource(R.string.app_logo)
    if (isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .background(
                    Color.Black
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.rotate(rotateDegrees),
                painter = painterResource(logoResId),
                contentDescription = logoContentDesc
            )
        }
    } else {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(Purple700, Purple500)
                    )
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.rotate(rotateDegrees),
                painter = painterResource(logoResId),
                contentDescription = logoContentDesc
            )
        }
    }
}

@Composable
@Preview
fun PreviewSplash() {
    Splash(0f)
}

@Composable
@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
fun PreviewSplashNightMode() {
    Splash(0f)
}
