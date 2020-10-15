package com.alvayonara.mealdb_alvayonarapuramandya.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.mealdb_alvayonarapuramandya.R
import com.alvayonara.mealdb_alvayonarapuramandya.model.FoodModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_slider.view.*

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private var listSlider = ArrayList<FoodModel>()

    fun setSlider(foods: List<FoodModel>?) {
        if (foods == null) return
        listSlider.clear()
        listSlider.addAll(foods)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder = SliderViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_slider, parent, false)
    )

    override fun getItemCount(): Int = listSlider.size

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) =
        holder.bindItem(listSlider[position])

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(food: FoodModel) {
            with(itemView) {
                Glide.with(context)
                    .load(food.foodThumb)
                    .into(iv_image_slider)
            }
        }
    }
}