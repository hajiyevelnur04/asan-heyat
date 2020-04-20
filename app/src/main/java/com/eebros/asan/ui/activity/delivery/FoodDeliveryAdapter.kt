package com.eebros.asan.ui.activity.delivery

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eebros.asan.R
import com.eebros.asan.data.remote.response.RestaurantResponseModel
import kotlinx.android.synthetic.main.store_item.view.*

class FoodDeliveryAdapter(
    private val foodList: ArrayList<RestaurantResponseModel>,
    private val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<FoodDeliveryAdapter.FoodViewHolder>(){

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurantResponseModel: RestaurantResponseModel, clickListener: (position: Int) -> Unit){
            with(restaurantResponseModel) {

                itemView.header.text = this.name
                itemView.rating.text = this.rating.toString()
                itemView.deliveryTime.text = this.deliveryTime

                itemView.minOrder.text = this.minOrder
                itemView.offerHome.text = this.offerText

                if(this.isOpen) {
                    itemView.storeStatus.text = "Open now"
                    itemView.storeStatus.setTextColor(Color.GREEN)
                } else {
                    itemView.storeStatus.text = "close now"
                    itemView.storeStatus.setTextColor(Color.RED)
                }

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
