package com.alvayonara.mealdb_alvayonarapuramandya.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alvayonara.mealdb_alvayonarapuramandya.R
import com.alvayonara.mealdb_alvayonarapuramandya.model.FoodModel
import com.alvayonara.mealdb_alvayonarapuramandya.utils.gone
import com.alvayonara.mealdb_alvayonarapuramandya.utils.visible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_food_detail.*

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var foodDetailViewModel: FoodDetailViewModel

    companion object {
        const val EXTRA_FOOD_ID = "extra_food_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)

        initToolbar()

        // Initialize ViewModel
        foodDetailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FoodDetailViewModel::class.java]

        initView()
    }

    private fun initView() {
        val extras = intent.extras
        if (extras != null) {
            val foodId = extras.getString(EXTRA_FOOD_ID)

            progress_bar_food_detail.visible()

            if (foodId != null) {
                foodDetailViewModel.setSelectedFood(foodId)

                foodDetailViewModel.foodDetail.observe(this, {
                    progress_bar_food_detail.gone()

                    if (it != null) {
                        populateFood(it[0])
                    }
                })
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun populateFood(food: FoodModel) {
        Glide.with(this)
            .load(food.foodThumb)
            .into(iv_food_detail)

        tv_food_name_detail.text = food.foodName
        tv_food_category.text = food.foodCategory
        tv_area.text = food.foodArea
        tv_food_instructions.text = food.foodInstruction
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}