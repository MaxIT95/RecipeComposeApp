package com.example.recipecomposeapp.ui.favorites.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.ui.theme.Dimensions
import com.example.recipecomposeapp.utils.ASSETS_URI_PREFIX

const val FAVORITES_IMAGE_URL = ASSETS_URI_PREFIX + "bcg_favorites.png"

@Composable
fun FavoritesScreen(innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding)) {
        ScreenHeader("ИЗБРАННОЕ", FAVORITES_IMAGE_URL)
        Spacer(Modifier.padding(vertical = 10.dp))
        LazyColumn(
            contentPadding = PaddingValues(horizontal = Dimensions.paddingLarge)
        ) {

        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FavoritesScreenPreview() {
    FavoritesScreen(PaddingValues(Dimensions.paddingLarge))
}