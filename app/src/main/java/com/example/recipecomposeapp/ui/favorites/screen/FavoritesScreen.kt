package com.example.recipecomposeapp.ui.favorites.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography

@Composable
fun FavoritesScreen(innerPadding: PaddingValues) {
    Column {
        ScreenHeader("ИЗБРАННОЕ ", R.drawable.bcg_favorites, innerPadding)
        Log.i("fuck", innerPadding.toString())

        LazyColumn(modifier = Modifier.padding(paddingValues = innerPadding)) {
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
        modifier = Modifier,
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
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
                modifier = Modifier.padding(5.dp),
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.titleMedium
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FavoritesScreenPreview() {
    FavoritesScreen(PaddingValues(16.dp))
}