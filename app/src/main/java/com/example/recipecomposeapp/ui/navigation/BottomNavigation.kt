package com.example.recipecomposeapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.ui.theme.Dimensions
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography
import com.example.recipecomposeapp.utils.FavoriteDataStoreManager

@Composable
fun BottomNavigation(
    onCategoriesClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier,
    favoriteDataStoreManager: FavoriteDataStoreManager
) {
    Row(modifier = modifier.fillMaxWidth()) {
        val modButton: Modifier = Modifier
            .weight(1f)

        NavigationButton(
            "КАТЕГОРИИ", onCategoriesClick,
            MaterialTheme.colorScheme.tertiary,
            modButton, null, null
        )
        NavigationButton(
            "ИЗБРАННОЕ", onFavoriteClick,
            MaterialTheme.colorScheme.error,
            modButton, R.drawable.ic_heart_empty,
            favoriteDataStoreManager
        )
    }
}

@Composable
fun NavigationButton(
    name: String, onClick: () -> Unit,
    color: Color,
    mod: Modifier,
    iconId: Int?,
    favoriteDataStoreManager: FavoriteDataStoreManager?
) {
    Button(
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(Dimensions.shapeSmall),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        modifier = mod
            .height(50.dp)
            .padding(Dimensions.paddingSmall)
    ) {
        Text(
            name,
            style = RecipesAppTypography.titleMedium
        )
        if (iconId != null && name == "ИЗБРАННОЕ" && favoriteDataStoreManager != null) {
            Spacer(modifier = Modifier.width(Dimensions.paddingMedium))

            val countFavorites by favoriteDataStoreManager.getFavoriteCountFlow()
                .collectAsState(initial = 0)

            if (countFavorites > 0) {
                Text(
                    countFavorites.toString(),
                    style = RecipesAppTypography.titleMedium
                )
            }

            Icon(
                painter = painterResource(iconId),
                contentDescription = "",
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun BottomNavigationPreview() {
    BottomNavigation({}, {}, Modifier, FavoriteDataStoreManager(LocalContext.current))
}