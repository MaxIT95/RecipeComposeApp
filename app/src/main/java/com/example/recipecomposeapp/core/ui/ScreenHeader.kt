package com.example.recipecomposeapp.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography

@Composable
fun ScreenHeader(text: String, imageId: Int, innerPaddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPaddingValues)
            .height(240.dp)
    ) {
        Image(
            painter = painterResource(imageId),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop,
            contentDescription = "фон"
        )
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart),
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.background,
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.displayLarge
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ScreenHeaderPreview() {
    ScreenHeader("КАТЕГОРИИ", R.drawable.bcg_categories, PaddingValues(16.dp))
}