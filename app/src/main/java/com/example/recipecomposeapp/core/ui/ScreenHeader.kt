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
import coil.size.Dimension
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.ui.theme.Dimensions
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography

@Composable
fun ScreenHeader(title: String, imagePainter: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        Image(
            painter = painterResource(imagePainter),
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop,
            contentDescription = "фон"
        )
        Surface(
            modifier = modifier
                .padding(Dimensions.paddingLarge)
                .align(Alignment.BottomStart),
            shape = RoundedCornerShape(Dimensions.shapeSmall),
            color = MaterialTheme.colorScheme.background,
        ) {
            Text(
                text = title,
                modifier = modifier.padding(horizontal = Dimensions.paddingLarge,
                    vertical = Dimensions.paddingMedium),
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.displayLarge
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ScreenHeaderPreview() {
    ScreenHeader("КАТЕГОРИИ", R.drawable.bcg_categories)
}