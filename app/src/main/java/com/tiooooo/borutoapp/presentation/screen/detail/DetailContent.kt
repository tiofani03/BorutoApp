package com.tiooooo.borutoapp.presentation.screen.detail

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tiooooo.borutoapp.R
import com.tiooooo.borutoapp.domain.model.Hero
import com.tiooooo.borutoapp.presentation.components.InfoBox
import com.tiooooo.borutoapp.presentation.components.OrderedList
import com.tiooooo.borutoapp.ui.theme.EXPANDED_RADIUS_LEVEL
import com.tiooooo.borutoapp.ui.theme.EXTRA_LARGE_PADDING
import com.tiooooo.borutoapp.ui.theme.INFO_ICON_SIZE
import com.tiooooo.borutoapp.ui.theme.LARGE_PADDING
import com.tiooooo.borutoapp.ui.theme.MEDIUM_PADDING
import com.tiooooo.borutoapp.ui.theme.MIN_SHEET_HEIGHT
import com.tiooooo.borutoapp.ui.theme.SMALL_PADDING
import com.tiooooo.borutoapp.ui.theme.titleColor
import com.tiooooo.borutoapp.utils.Constants

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterialApi
@Composable
fun DetailContent(
    navController: NavHostController,
    selectedHero: Hero,
    colors: Map<String, String>,
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#FFFFFF") }

    LaunchedEffect(key1 = selectedHero) {
        vibrant = colors["vibrant"] ?: "#000000"
        darkVibrant = colors["darkVibrant"] ?: "#000000"
        onDarkVibrant = colors["onDarkVibrant"] ?: "#FFFFFF"
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(parseColor(darkVibrant))
    )


    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction
    val radiusAnim by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f) EXTRA_LARGE_PADDING else EXPANDED_RADIUS_LEVEL
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim,
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            BottomSheetContent(
                selectedHero = selectedHero,
                infoBoxIconColor = Color(parseColor(vibrant)),
                sheetBackgroundColor = Color(parseColor(darkVibrant)),
                contentColor = Color(parseColor(onDarkVibrant)),
            )
        },
        content = {
            BackgroundContent(
                heroImage = selectedHero.image,
                imageFraction = currentSheetFraction,
                backgroundColor = Color(parseColor(darkVibrant)),
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
    )
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor,
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = contentColor,
            )
            Text(
                modifier = Modifier.weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = stringResource(id = R.string.power),
                textColor = contentColor,
            )

            InfoBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = stringResource(id = R.string.month),
                textColor = contentColor,
            )

            InfoBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = stringResource(id = R.string.birthday),
                textColor = contentColor,
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
        )

        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = 7,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            OrderedList(
                title = stringResource(id = R.string.family),
                listItems = selectedHero.family,
                textColor = contentColor,
            )
            OrderedList(
                title = stringResource(id = R.string.abilities),
                listItems = selectedHero.abilities,
                textColor = contentColor,
            )
            OrderedList(
                title = stringResource(id = R.string.nature_types),
                listItems = selectedHero.natureTypes,
                textColor = contentColor,
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit,
) {
    val imageUrl = Constants.BASE_URl + heroImage
    val painter = rememberImagePainter(imageUrl) {
        error(R.drawable.ic_placeholder)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + 0.4f)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = onCloseClicked
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_icon),
                    tint = Color.White,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == Expanded && targetValue == Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == Expanded -> 1f - fraction
            currentValue == Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Composable
@Preview(showBackground = true)
fun BottomSheetContentPreview() {
    val hero = Hero(
        id = 15,
        name = "Koji",
        image = "/images/koji.jpg",
        about = "Koji Kashin (果心居士, Kashin Koji) is a clone of Jiraiya that was created by Amado for the purpose of killing Isshiki Ōtsutsuki. A former Inner of Kara, he was in charge of the sector on the outskirts of the Land of Fire. An enigmatic man, Koji has a very stoic and straightforward nature that follows a no-nonsense view. Arrogant as he may appear, he has consistently shown himself to be a very rational and perceptive man.",
        rating = 4.5,
        power = 90,
        month = "Jan",
        day = "1st",
        family = listOf(
            "Jiraiya"
        ),
        abilities = listOf(
            "Senin Mode",
            "Rasengan",
            "Shadow Clone"
        ),
        natureTypes = listOf(
            "Fire",
            "Earth"
        )
    )
    BottomSheetContent(selectedHero = hero)
}
