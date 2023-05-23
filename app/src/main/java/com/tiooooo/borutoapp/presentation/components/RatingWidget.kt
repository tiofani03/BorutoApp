package com.tiooooo.borutoapp.presentation.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tiooooo.borutoapp.R
import com.tiooooo.borutoapp.ui.theme.EXTRA_SMALL_PADDING
import com.tiooooo.borutoapp.ui.theme.StarColor

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 2f,
    spaceBetween: Dp = EXTRA_SMALL_PADDING,
) {
    val result = calculateStar(rating = rating)

    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        result["filledStars"]?.let {
            repeat(it) {
                FilledStar(
                    starPath = starPath,
                    starPathBound = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
        result["halfFilledStars"]?.let {
            repeat(it) {
                HalfFilledStar(
                    starPath = starPath,
                    starPathBound = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
        result["emptyStars"]?.let {
            repeat(it) {
                EmptyStar(
                    starPath = starPath,
                    starPathBound = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
    }


}

@Composable
fun FilledStar(
    starPath: Path,
    starPathBound: Rect,
    scaleFactor: Float,
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBound.width
            val pathHeight = starPathBound.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left, top) {
                drawPath(
                    path = starPath,
                    color = StarColor,
                )
            }
        }
    }
}

@Composable
fun HalfFilledStar(
    starPath: Path,
    starPathBound: Rect,
    scaleFactor: Float,
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBound.width
            val pathHeight = starPathBound.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left, top) {
                drawPath(
                    path = starPath,
                    color = Color.LightGray.copy(0.5f),
                )
                clipPath(path = starPath) {
                    drawRect(
                        color = StarColor,
                        size = Size(
                            width = starPathBound.maxDimension / 1.7f,
                            height = starPathBound.maxDimension * scaleFactor,
                        )
                    )
                }
            }
        }
    }
}


@Composable
fun EmptyStar(
    starPath: Path,
    starPathBound: Rect,
    scaleFactor: Float,
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBound.width
            val pathHeight = starPathBound.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left, top) {
                drawPath(
                    path = starPath,
                    color = Color.LightGray.copy(0.5f)
                )
            }
        }
    }
}

@Composable
fun calculateStar(
    rating: Double,
): Map<String, Int> {
    val maxStars by remember { mutableStateOf(5) }
    var filledStars by remember { mutableStateOf(0) }
    var halfFilledStars by remember { mutableStateOf(0) }
    var emptyStars by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = rating) {
        val (firstNumber, lastNumber) = rating.toString()
            .split(".")
            .map { it.toInt() }

        if (firstNumber in 0..5 && lastNumber in 0..9) {
            filledStars = firstNumber
            if (lastNumber in 1..5) {
                halfFilledStars++
            }
            if (lastNumber in 6..9) {
                filledStars++
            }
            if (firstNumber == 5 && lastNumber > 0) {
                emptyStars = 5
                filledStars = 0
                halfFilledStars = 0
            }
        } else {
            Log.d("RatingWidget", "Invalid rating number.")
        }
    }

    emptyStars = maxStars - (filledStars + halfFilledStars)
    return mapOf(
        "filledStars" to filledStars,
        "halfFilledStars" to halfFilledStars,
        "emptyStars" to emptyStars
    )
}

@Composable
@Preview(showBackground = true)
fun FilledStarPreview() {
    RatingWidget(modifier = Modifier, rating = 3.4, scaleFactor = 2f)
}

@Composable
@Preview(showBackground = true)
fun HalfFilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }
    HalfFilledStar(
        starPath = starPath,
        starPathBound = starPathBounds,
        scaleFactor = 3f
    )
}

@Composable
@Preview(showBackground = true)
fun EmptyStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }
    EmptyStar(
        starPath = starPath,
        starPathBound = starPathBounds,
        scaleFactor = 3f
    )
}

