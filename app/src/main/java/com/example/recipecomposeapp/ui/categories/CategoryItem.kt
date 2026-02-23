package com.example.recipecomposeapp.ui.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.ui.categories.model.CategoryUiModel
import com.example.recipecomposeapp.ui.theme.Dimensions
import com.example.recipecomposeapp.ui.theme.Dimensions.cardLargeSize
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography

@Composable
fun CategoryItem(model: CategoryUiModel, onClick: (CategoryUiModel) -> Unit) {
    Card(
        modifier = Modifier
            .height(cardLargeSize)
            .fillMaxWidth(),
        onClick = {
            onClick(model)
        },
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(Dimensions.shapeMedium)
    ) {
        Column {
            AsyncImage(
                model = model.imageUrl,
                contentDescription = "фон",
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.img_error),
                placeholder = painterResource(R.drawable.img_placeholder),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
            )
            Text(
                text = model.title,
                modifier = Modifier.padding(Dimensions.paddingSmall),
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.bodyMedium
            )
            Text(
                text = model.description,
                modifier = Modifier.padding(Dimensions.paddingSmall),
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.bodySmall
            )
        }
    }
}