package com.example.recipecomposeapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.ui.theme.RecipesAppTypography

@Composable
fun BottomNavigation(
    onCategoriesClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        val modButton: Modifier = Modifier
            .weight(1f)

        NavigationButton(
            "КАТЕГОРИИ", onCategoriesClick,
            MaterialTheme.colorScheme.tertiary,
            modButton, null
        )
        NavigationButton(
            "ИЗБРАННОЕ", onFavoriteClick,
            MaterialTheme.colorScheme.error,
            modButton, R.drawable.ic_heart_empty
        )
    }
}

@Composable
fun NavigationButton(
    name: String, onClick: () -> Unit,
    color: Color,
    mod: Modifier,
    iconId: Int?
) {
    Button(
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        modifier = mod
            .height(50.dp)
            .padding(5.dp)
    ) {
        Text(
            name,
            style = RecipesAppTypography.titleMedium
        )
        if (iconId != null) {
            Spacer(modifier = Modifier.width(8.dp))
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
    BottomNavigation({}, {}, Modifier)
}