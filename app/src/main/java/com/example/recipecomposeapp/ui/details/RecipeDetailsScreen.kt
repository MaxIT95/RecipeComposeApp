package com.example.recipecomposeapp.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.ui.recipes.model.IngredientUiModel
import com.example.recipecomposeapp.ui.recipes.model.RecipeUiModel
import com.example.recipecomposeapp.ui.theme.Dimensions
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography
import com.example.recipecomposeapp.utils.shareRecipe
import kotlin.math.roundToInt

@Composable
fun RecipeDetailsScreen(
    recipe: RecipeUiModel,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    var currentPortions by remember { mutableStateOf(recipe.portions) }

    val commonModifier = Modifier.padding(
        horizontal = Dimensions.paddingLarge,
        vertical = Dimensions.paddingLarge
    )

    val scaledIngredients = remember(currentPortions) {
        recipe.ingredients.map { ingredient ->
            ingredient.copy(
                quantity = (ingredient.quantity.toDouble() * currentPortions).toString()
            )
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // 1 картинка рецепта + название (аналог как в категории)
        ScreenHeader(
            recipe.title.uppercase(),
            recipe.imageUrl,
            showShareButton = true,
            onShareClick = { shareRecipe(context, recipe.id, recipe.title) },
            showFavoritesButton = true,
            onFavoritesClick = {}) // todo пока заглушка
        // 2 слово "Ингридиенты" + слайдер порций
        PortionsSelector(
            currentPortions, { currentPortions = it },
            commonModifier
        )
        // 3 список ингредиентов
        IngredientsList(ingredients = scaledIngredients, commonModifier)

        Spacer(Modifier.padding(vertical = 5.dp))
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "СПОСОБ ПРИГОТОВЛЕНИЯ",
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.displayLarge,
            )
        }
        Spacer(Modifier.padding(vertical = 5.dp))
        InstructionsList(recipe.method, commonModifier)
    }
}

@Composable
fun PortionsSelector(currentPortions: Int, onPortionsChange: (Int) -> Unit, modifier: Modifier) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "ИНГРЕДИЕНТЫ",
            color = MaterialTheme.colorScheme.primary,
            style = RecipesAppTypography.displayLarge
        )
        Spacer(Modifier.padding(vertical = 5.dp))

        val portionsText = pluralStringResource(
            R.plurals.portions_count,
            currentPortions,
            currentPortions
        )

        Text(
            text = portionsText,
            color = MaterialTheme.colorScheme.secondary,
            style = RecipesAppTypography.titleMedium
        )
        PortionsSlider(currentPortions, onPortionsChange)
    }
}

@Composable
fun PortionsSlider(
    currentPortions: Int,
    onPortionsChange: (Int) -> Unit
) {
    Slider(
        value = currentPortions.toFloat(),
        onValueChange = { onPortionsChange(it.roundToInt()) },
        valueRange = 1f..12f
    )
}

@Composable
fun IngredientsList(
    ingredients: List<IngredientUiModel>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(Dimensions.paddingLarge)
    ) {
        ingredients.forEachIndexed { index, item ->
            IngredientItem(item, modifier)
            if (index < ingredients.lastIndex) {
                HorizontalDivider(
                    Modifier.padding(
                        horizontal = Dimensions.paddingLarge
                    )
                )
            }
        }
    }
}

@Composable
fun IngredientItem(ingredient: IngredientUiModel, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = ingredient.name,
            color = MaterialTheme.colorScheme.secondary,
            style = RecipesAppTypography.bodyMedium
        )

        Text(
            text = "${ingredient.quantity} ${ingredient.unitOfMeasure}",
            color = MaterialTheme.colorScheme.secondary,
            style = RecipesAppTypography.bodyMedium
        )
    }
}

@Composable
fun InstructionsList(instructions: List<String>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(Dimensions.paddingLarge)
    ) {
        instructions.forEachIndexed { index, step ->
            Text(
                text = "${index + 1} $step}",
                color = MaterialTheme.colorScheme.secondary,
                style = RecipesAppTypography.bodyMedium
            )
            if (index < instructions.lastIndex) {
                HorizontalDivider(
                    Modifier.padding(
                        horizontal = Dimensions.paddingLarge
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RecipeDetailsScreenPreview() {
    RecipeDetailsScreen(
        RecipeUiModel(
            1, "Рецепт",
            listOf(), listOf(), false, ""
        ),
        paddingValues = PaddingValues(16.dp),
    )
}