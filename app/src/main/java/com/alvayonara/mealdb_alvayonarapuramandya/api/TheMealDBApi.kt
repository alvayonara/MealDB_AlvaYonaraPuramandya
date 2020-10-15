package com.alvayonara.mealdb_alvayonarapuramandya.api

import com.alvayonara.mealdb_alvayonarapuramandya.model.FoodModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDBApi {

    @GET("api/json/v1/1/filter.php?c=Seafood")
    fun getFoods(
    ): Call<FoodModel>

    @GET("api/json/v1/1/lookup.php?")
    fun getFoodById(
        @Query("i") foodId: String?,
    ): Call<FoodModel>
}