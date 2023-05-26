package com.tiooooo.borutoapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.tiooooo.borutoapp.ui.theme.SMALL_PADDING
import com.tiooooo.borutoapp.ui.theme.titleColor

@Composable
fun OrderedList(
    title: String,
    listItems: List<String>,
    textColor: Color,
) {
    Column {
        Text(
            modifier = Modifier.padding(bottom = SMALL_PADDING),
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        listItems.forEachIndexed { index, s ->
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "${index + 1}. $s",
                color = textColor,
                fontSize = MaterialTheme.typography.body1.fontSize,
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun OrderedListPreview() {
    OrderedList(
        title = "Family",
        listItems = listOf("Fugaku", "Mikoto", "Itachi"),
        textColor = MaterialTheme.colors.titleColor
    )
}
