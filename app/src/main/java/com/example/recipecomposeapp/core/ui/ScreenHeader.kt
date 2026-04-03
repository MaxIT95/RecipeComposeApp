package com.example.recipecomposeapp.core.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.ui.theme.Dimensions
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography

@Composable
fun ScreenHeader(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    showFavoritesButton: Boolean = false,
    onFavoritesClick: () -> Unit = {}, //todo заглушка, позже реализовую
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
                onFavoritesClick
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
    onShareClick: () -> Unit
) {

    if (showFavoritesButton) {
        //нужно получать состояния кнопки (todo будем брать из ВМ избранного)
        var isAddedToFavorites by remember { mutableStateOf(false) }

        val iconState =
            if (isAddedToFavorites) R.drawable.ic_heart else R.drawable.ic_heart_empty

        IconButton(
            onClick = { isAddedToFavorites = !isAddedToFavorites },
        ) {
            Icon(
                painter = painterResource(iconState),
                contentDescription = "Добавить в избранное",
                tint = Color.Unspecified
            )
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