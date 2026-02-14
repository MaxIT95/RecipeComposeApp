package com.example.recipecomposeapp.ui.favorites.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.ui.theme.Dimensions
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography

@Composable
fun FavoritesScreen(innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding)) {
        ScreenHeader("ИЗБРАННОЕ ", R.drawable.bcg_favorites)
        Spacer(Modifier.padding(vertical = 10.dp))
        LazyColumn(
            contentPadding = PaddingValues(horizontal = Dimensions.paddingLarge)
        ) {
            item {
                ReceiptCard(
                    "Классический гамбургер",
                    R.drawable.burger_hamburger
                )
            }
        }
    }
}

@Composable
fun ReceiptCard(name: String, imageId: Int) {
    Card(
        modifier = Modifier.padding(vertical = Dimensions.paddingMedium),
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(Dimensions.paddingLarge)
    ) {
        Column {
            Image(
                painter = painterResource(imageId),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                contentDescription = "фон"
            )
            Text(
                text = name,
                modifier = Modifier.padding(Dimensions.paddingSmall),
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.titleMedium
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FavoritesScreenPreview() {
    FavoritesScreen(PaddingValues(Dimensions.paddingLarge))
}