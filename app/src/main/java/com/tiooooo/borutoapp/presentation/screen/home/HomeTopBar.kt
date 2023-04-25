package com.tiooooo.borutoapp.presentation.screen.home

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tiooooo.borutoapp.R
import com.tiooooo.borutoapp.ui.theme.topAppBarBackgroundColor
import com.tiooooo.borutoapp.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(
    onSearchClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = MaterialTheme.colors.topAppBarContentColor,
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon),
                )
            }
        }
    )
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar(onSearchClick = { })
}
