package com.eebros.asan.ui.activity.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eebros.asan.R
import com.eebros.asan.model.ServicePersonModel

class SelectedServiceAdapter(
    private val serviceList: ArrayList<ServicePersonModel>,
    private val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<SelectedServiceAdapter.FoodViewHolder>(){

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(servicePersonModel: ServicePersonModel, clickListener: (position: Int) -> Unit){
            with(servicePersonModel) {

                //itemView.header.text = this.name
                itemView.setOnClickListener {
                    clickListener.invoke(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.service_person_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = serviceList.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        return holder.bind(serviceList[position], clickListener)
    }

}
