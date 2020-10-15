package com.alvayonara.mealdb_alvayonarapuramandya.api

import android.app.Application
import com.alvayonara.mealdb_alvayonarapuramandya.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository : Application() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_TMDB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val theMealDB: TheMealDBApi = retrofit.create(TheMealDBApi::class.java)
}