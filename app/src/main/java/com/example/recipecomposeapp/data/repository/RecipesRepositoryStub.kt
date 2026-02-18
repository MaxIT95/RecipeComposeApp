package com.example.recipecomposeapp.data.repository

import com.example.recipecomposeapp.data.model.CategoryDto
import com.example.recipecomposeapp.data.model.IngredientDto
import com.example.recipecomposeapp.data.model.RecipeDto

object RecipesRepositoryStub {
    private val categoryList = initCategoryList()

    private val burgerRecipes = initRecipeList()

    fun getCategories(): List<CategoryDto> {
        return categoryList
    }

    fun getRecipesByCategoryId(categoryId: Int): List<RecipeDto> {
        return when (categoryId) {
            0 -> burgerRecipes  // Имитация GET /category/0/recipes
            else -> emptyList() // Остальные категории пока пустые
        }
    }

    private fun initCategoryList(): List<CategoryDto> {
        return listOf(
            CategoryDto(
                id = 0, title = "Бургеры",
                description = "Рецепты всех популярных видов бургеров",
                imageUrl = "burger.png"
            ),
            CategoryDto(
                id = 1, title = "Десерты",
                description = "Самые вкусные рецепты десертов специально для вас",
                imageUrl = "dessert.png"
            ),
            CategoryDto(
                id = 2, title = "Пицца",
                description = "Пицца на любой вкус и цвет. Лучшая подборка для тебя",
                imageUrl = "pizza.png"
            ),
            CategoryDto(
                id = 3, title = "Рыба",
                description = "Печеная, жареная, сушеная, любая рыба на твой вкус",
                imageUrl = "fish.png"
            ),
            CategoryDto(
                id = 4, title = "Супы",
                description = "От классики до экзотики: мир в одной тарелке",
                imageUrl = "soup.png"
            ),
            CategoryDto(
                id = 5, title = "Салаты",
                description = "Хрустящий калейдоскоп под соусом вдохновения",
                imageUrl = "salad.png"
            )
        )
    }

    private fun initRecipeList(): List<RecipeDto> {
        return listOf(
            RecipeDto(
                id = 0,
                name = "Классический бургер с говядиной",
                ingredients = listOf(
                    IngredientDto(
                        count = 1,
                        unitOfChange = "кг",
                        description = "говяжий фарш"
                    ),
                    IngredientDto(
                        count = 1,
                        unitOfChange = "шт",
                        description = "луковица, мелко нарезанная"
                    ),
                    IngredientDto(
                        count = 2,
                        unitOfChange = "зубч",
                        description = "чеснок, измельченный"
                    ),
                    IngredientDto(
                        count = 4,
                        unitOfChange = "шт",
                        description = "листа салата"
                    ),
                    IngredientDto(
                        count = 1,
                        unitOfChange = "шт",
                        description = "помидор, нарезанный кольцами"
                    ),
                    IngredientDto(
                        count = 2,
                        unitOfChange = "ст. л.",
                        description = "горчица"
                    ),
                    IngredientDto(
                        count = 2,
                        unitOfChange = "ст. л.",
                        description = "кетчуп"
                    ),
                    IngredientDto(
                        count = 1,
                        unitOfChange = "щепотка",
                        description = "соль и черный перец"
                    )
                ),
                stepsCooking = "1. В глубокой миске смешайте говяжий фарш, лук, чеснок, соль и перец. Разделите фарш на 4 равные части и сформируйте котлеты.\n" +
                        "2. Разогрейте сковороду на среднем огне. Обжаривайте котлеты с каждой стороны в течение 4-5 минут или до желаемой степени прожарки.\n" +
                        "3. В то время как котлеты готовятся, подготовьте булочки. Разрежьте их пополам и обжарьте на сковороде до золотистой корочки.\n" +
                        "4. Смазать нижние половинки булочек горчицей и кетчупом, затем положите лист салата, котлету, кольца помидора и закройте верхней половинкой булочки.\n" +
                        "5. Подавайте бургеры горячими с картофельными чипсами или картофельным пюре.",
                imageUrl = "burger-hamburger.png"
            ),
            RecipeDto(
                id = 1,
                name = "Чизбургер с беконом",
                ingredients = listOf(
                    IngredientDto(
                        count = 1,
                        unitOfChange = "кг",
                        description = "говяжий фарш"
                    ),
                    IngredientDto(
                        count = 4,
                        unitOfChange = "шт",
                        description = "ломтика бекона"
                    ),
                    IngredientDto(
                        count = 4,
                        unitOfChange = "шт",
                        description = "ломтика сыра чеддер"
                    ),
                    IngredientDto(
                        count = 4,
                        unitOfChange = "шт",
                        description = "булочки для бургера"
                    ),
                    IngredientDto(
                        count = 1,
                        unitOfChange = "шт",
                        description = "помидор, нарезанный"
                    ),
                    IngredientDto(
                        count = 1,
                        unitOfChange = "",
                        description = "майонез и кетчуп"
                    )
                ),
                stepsCooking = "1. Обжарьте бекон на сковороде до хрустящей корочки, отложите на бумажное полотенце.\n" +
                        "2. Сформируйте из фарша 4 котлеты, обжарьте с каждой стороны по 4 минуты.\n" +
                        "3. За минуту до готовности положите на каждую котлету по ломтику сыра, чтобы он расплавился.\n" +
                        "4. Соберите бургер: булочка, майонез, котлета с сыром, бекон, помидор, кетчуп.\n" +
                        "5. Подавайте горячими.",
                imageUrl = "burger-cheeseburger.png"
            )
        )
    }
}