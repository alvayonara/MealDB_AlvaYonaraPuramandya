package com.alvayonara.mealdb_alvayonarapuramandya.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alvayonara.mealdb_alvayonarapuramandya.api.ApiRepository
import com.alvayonara.mealdb_alvayonarapuramandya.model.FoodModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {

    fun getFoods(): LiveData<List<FoodModel>> {
        val foodResults = MutableLiveData<List<FoodModel>>()

        ApiRepository().theMealDB.getFoods()
            .enqueue(object : Callback<FoodModel> {
                override fun onResponse(call: Call<FoodModel>, response: Response<FoodModel>) {
                    foodResults.postValue(
                        response.body()!!.foods
                    )
                }

                override fun onFailure(call: Call<FoodModel>, t: Throwable) {
                    foodResults.postValue(null)
                }
            })

        return foodResults
    }
}