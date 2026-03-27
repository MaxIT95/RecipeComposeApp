package com.example.recipecomposeapp.ui.recipes

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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.ui.recipes.model.RecipeUiModel
import com.example.recipecomposeapp.ui.theme.Dimensions
import com.example.recipecomposeapp.ui.theme.Dimensions.cardMediumHeight
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography

@Composable
fun ReceiptItem(receipt: RecipeUiModel, onClick: (Int, RecipeUiModel) -> Unit) {
    Card(
        modifier = Modifier.padding(vertical = Dimensions.paddingMedium),
        onClick = { onClick(receipt.id, receipt) },
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(Dimensions.paddingLarge)
    ) {
        Column {
            AsyncImage(
                model = receipt.imageUrl,
                contentDescription = "фон",
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.img_error),
                placeholder = painterResource(R.drawable.img_placeholder),
                modifier = Modifier.fillMaxWidth().height(cardMediumHeight)
            )

            Text(
                text = receipt.title,
                modifier = Modifier.padding(Dimensions.paddingSmall),
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.titleMedium
            )
        }
    }
}
