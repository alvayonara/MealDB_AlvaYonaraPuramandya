package com.alvayonara.mealdb_alvayonarapuramandya.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.mealdb_alvayonarapuramandya.R
import com.alvayonara.mealdb_alvayonarapuramandya.adapter.FoodAdapter
import com.alvayonara.mealdb_alvayonarapuramandya.adapter.SliderAdapter
import com.alvayonara.mealdb_alvayonarapuramandya.utils.Tools
import com.alvayonara.mealdb_alvayonarapuramandya.utils.gone
import com.alvayonara.mealdb_alvayonarapuramandya.utils.visible
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initToolbar()

        // Initialize ViewModel
        dashboardViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DashboardViewModel::class.java]

        getFoodsData()
    }

    private fun initToolbar() {
        // Hide action bar
        supportActionBar?.hide()

        Tools.setSystemBarColor(this, android.R.color.white)
        Tools.setSystemBarLight(this)
    }

    private fun getFoodsData() {
        // Initialize Adapter
        val foodAdapter = FoodAdapter()
        val sliderAdapter = SliderAdapter()

        progress_bar_dashboard.visible()

        // Using LiveData to observe foods data
        dashboardViewModel.getFoods().observe(this, {
            progress_bar_dashboard.gone()

            if (it != null) {
                foodAdapter.setFoods(it)
                foodAdapter.notifyDataSetChanged()

                sliderAdapter.setSlider(it)
                sliderAdapter.notifyDataSetChanged()
            }
        })

        with(rv_foods) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = foodAdapter
        }

        with(rv_food_slider) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = sliderAdapter
        }
    }
}