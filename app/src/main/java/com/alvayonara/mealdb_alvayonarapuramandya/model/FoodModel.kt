package com.alvayonara.mealdb_alvayonarapuramandya.model

import com.google.gson.annotations.SerializedName

data class FoodModel(
    @SerializedName("meals")
    val foods: List<FoodModel>? = null,

    @SerializedName("idMeal")
    var foodId: String? = null,

    @SerializedName("strMeal")
    var foodName: String? = null,

    @SerializedName("strMealThumb")
    var foodThumb: String? = null,

    @SerializedName("strCategory")
    var foodCategory: String? = null,

    @SerializedName("strArea")
    var foodArea: String? = null,

    @SerializedName("strInstructions")
    var foodInstruction: String? = null,
)