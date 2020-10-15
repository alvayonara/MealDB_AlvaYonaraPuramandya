package com.alvayonara.mealdb_alvayonarapuramandya.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alvayonara.mealdb_alvayonarapuramandya.api.ApiRepository
import com.alvayonara.mealdb_alvayonarapuramandya.model.FoodModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDetailViewModel : ViewModel() {

    val foodId = MutableLiveData<String>()

    fun setSelectedFood(foodId: String) {
        this.foodId.value = foodId
    }

    var foodDetail: LiveData<List<FoodModel>> =
        Transformations.switchMap(foodId) { mFoodId ->
            getFoodById(mFoodId)
        }

    fun getFoodById(foodId: String): LiveData<List<FoodModel>> {
        val foodResult = MutableLiveData<List<FoodModel>>()

        ApiRepository().theMealDB.getFoodById(foodId)
            .enqueue(object : Callback<FoodModel> {
                override fun onResponse(call: Call<FoodModel>, response: Response<FoodModel>) {
                    foodResult.postValue(
                        response.body()!!.foods
                    )
                }

                override fun onFailure(call: Call<FoodModel>, t: Throwable) {
                    foodResult.postValue(null)
                }
            })

        return foodResult
    }
}