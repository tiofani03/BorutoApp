package com.tiooooo.borutoapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.tiooooo.borutoapp.R
import com.tiooooo.borutoapp.domain.model.Hero
import com.tiooooo.borutoapp.navigation.Screen
import com.tiooooo.borutoapp.presentation.components.RatingWidget
import com.tiooooo.borutoapp.presentation.components.ShimmerEffect
import com.tiooooo.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import com.tiooooo.borutoapp.ui.theme.LARGE_PADDING
import com.tiooooo.borutoapp.ui.theme.MEDIUM_PADDING
import com.tiooooo.borutoapp.ui.theme.SMALL_PADDING
import com.tiooooo.borutoapp.ui.theme.topAppBarContentColor
import com.tiooooo.borutoapp.utils.Constants

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    navController: NavHostController,
) {
    val result = handlePagingResult(heroes = heroes)

    if (result){
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = heroes,
                key = { hero ->
                    hero.id
                }
            ) { hero ->
                hero?.let {
                    HeroItem(hero = it, navController = navController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Hero>,
): Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null ->{
                EmptyScreen(error = error) {

                }
                false
            }
            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun HeroItem(
    hero: Hero,
    navController: NavHostController,
) {
    val painter = rememberImagePainter(data = "${Constants.BASE_URl}${hero.image}") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passHeroId(heroId = hero.id))
            },
        contentAlignment = Alignment.BottomStart,
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(id = R.string.hero_image),
                contentScale = ContentScale.Crop,
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = hero.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating,
                    )
                    Text(
                        text = "(${hero.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun HeroItemPreview() {
    HeroItem(
        hero = Hero(
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
        ),
        navController = rememberNavController()
    )
}
