package com.example.recipecomposeapp.ui.categories.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography

@Composable
fun CategoriesScreen(innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding)) {
        ScreenHeader("КАТЕГОРИИ ", R.drawable.bcg_categories)
        Column {

            Spacer(Modifier.padding(vertical = 15.dp))

//            val categories = mutableListOf<CategoryInfo>()
//            CategoryCards(categories)
        }
    }
}

// заготовка для категорий
@Composable
fun CategoryCards(categories: List<CategoryInfo>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp),
        content = {
            items(categories) { category ->
                CategoryCard(
                    category.name,
                    category.description,
                    category.imageId
                )
            }
        }
    )
}

// дто для отрисовки карты категории (скорее всего перенесется в viewModel в будущем)
data class CategoryInfo(val name: String, val description: String, val imageId: Int)

@Composable
fun CategoryCard(
    category: String, description: String,
    imageId: Int
) {
    Card(
        modifier = Modifier
            .height(220.dp)
            .width(150.dp),
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
                text = category,
                modifier = Modifier.padding(5.dp),
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.bodyMedium
            )
            Text(
                text = description,
                modifier = Modifier.padding(5.dp),
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.bodySmall
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CategoriesScreenPreview() {
    CategoriesScreen(PaddingValues(16.dp))
}