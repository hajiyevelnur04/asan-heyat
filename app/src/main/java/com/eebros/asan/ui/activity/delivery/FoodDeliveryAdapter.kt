package com.eebros.asan.ui.activity.delivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eebros.asan.R

class FoodDeliveryAdapter(
    private val foodList: ArrayList<Int>,
    private val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<FoodDeliveryAdapter.FoodViewHolder>(){

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(stories: Int, clickListener: (position: Int) -> Unit){
            with(stories) {
                //val byteArray = Base64.decode(stories, Base64.DEFAULT)

                itemView.setOnClickListener {
                    clickListener.invoke(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.store_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = foodList.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        return holder.bind(foodList[position], clickListener)
    }

}
