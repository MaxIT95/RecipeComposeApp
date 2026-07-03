package com.example.recipecomposeapp.core.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.core.ui.theme.Dimensions
import com.example.recipecomposeapp.core.ui.theme.RecipesAppTypography

@Composable
fun ScreenHeader(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    showFavoritesButton: Boolean = false,
    isFavorite: Boolean = false,
    onFavoritesClick: () -> Unit = {},
    showShareButton: Boolean = false,
    onShareClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "фон",
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.img_error),
            placeholder = painterResource(R.drawable.img_placeholder),
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.TopCenter),
        )

        Row(
            modifier = modifier
                .padding(Dimensions.paddingLarge)
                .align(Alignment.TopEnd)
        ) {
            ShareButton(
                showShareButton,
                onShareClick
            )

            FavoritesButton(
                showFavoritesButton,
                onFavoritesClick,
                isFavorite = isFavorite
            )
        }

        Surface(
            modifier = modifier
                .padding(Dimensions.paddingLarge)
                .align(Alignment.BottomStart),
            shape = RoundedCornerShape(Dimensions.shapeSmall),
            color = MaterialTheme.colorScheme.background,
        ) {
            Text(
                text = title,
                modifier = modifier.padding(
                    horizontal = Dimensions.paddingLarge,
                    vertical = Dimensions.paddingMedium
                ),
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.displayLarge
            )
        }
    }
}

@Composable
fun FavoritesButton(
    showFavoritesButton: Boolean = false,
    onFavoritesClick: () -> Unit,
    isFavorite: Boolean
) {

    if (showFavoritesButton) {
        IconButton(
            onClick = onFavoritesClick,
        ) {
            Crossfade(
                targetState = isFavorite,
                animationSpec = tween(durationMillis = 300),
                label = "favorite_animation"
            ) { isCurrentlyFavorite ->
                // Lambda получает текущее значение targetState
                // При изменении isFavorite, Crossfade плавно переключит между двумя иконками
                val heartIcon = rememberVectorPainter(
                    image = ImageVector.vectorResource(
                        id = if (isCurrentlyFavorite) R.drawable.ic_heart else R.drawable.ic_heart_empty
                    )
                )

                Icon(
                    painter = heartIcon,
                    contentDescription = "Favorite",
                    tint = Color.Unspecified // Сохраняет оригинальные цвета из drawable
                )
            }
        }
    }
}


@Composable
fun ShareButton(
    showShareButton: Boolean = false,
    onShareClick: () -> Unit,
) {

    if (showShareButton) {

        IconButton(
            onClick = onShareClick
        ) {
            Icon(
                painter = painterResource(R.drawable.share),
                contentDescription = "Добавить в избранное",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ScreenHeaderPreview() {
    ScreenHeader(
        "КАТЕГОРИИ", "", showFavoritesButton = true,
        showShareButton = true
    )
}