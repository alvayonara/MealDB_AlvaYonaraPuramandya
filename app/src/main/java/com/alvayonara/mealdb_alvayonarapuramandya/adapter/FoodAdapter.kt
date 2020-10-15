package com.alvayonara.mealdb_alvayonarapuramandya.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.mealdb_alvayonarapuramandya.R
import com.alvayonara.mealdb_alvayonarapuramandya.model.FoodModel
import com.alvayonara.mealdb_alvayonarapuramandya.ui.detail.FoodDetailActivity
import com.alvayonara.mealdb_alvayonarapuramandya.ui.detail.FoodDetailActivity.Companion.EXTRA_FOOD_ID
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row_food.view.*

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private var listFoods = ArrayList<FoodModel>()

    fun setFoods(foods: List<FoodModel>?) {
        if (foods == null) return
        listFoods.clear()
        listFoods.addAll(foods)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodViewHolder = FoodViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_food, parent, false)
    )

    override fun getItemCount(): Int = listFoods.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) =
        holder.bindItem(listFoods[position])

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(food: FoodModel) {
            with(itemView) {
                Glide.with(context)
                    .load(food.foodThumb)
                    .into(iv_food)

                tv_food.text = food.foodName

                itemView.setOnClickListener {
                    val intent = Intent(context, FoodDetailActivity::class.java).putExtra(
                        EXTRA_FOOD_ID,
                        food.foodId
                    )
                    context.startActivity(intent)
                }
            }
        }
    }
}