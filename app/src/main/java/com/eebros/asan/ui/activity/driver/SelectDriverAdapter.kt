package com.eebros.asan.ui.activity.driver

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eebros.asan.R
import com.eebros.asan.model.RiderCarListModel
import kotlinx.android.synthetic.main.taxi_ride.view.*

class SelectDriverAdapter(
    private val riderCarListModel: ArrayList<RiderCarListModel>,
    private val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<SelectDriverAdapter.SelectDriverViewHolder>(){

    private var lastCheckedPos = 0

    class SelectDriverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(riderCarListModel: RiderCarListModel, clickListener: (position: Int) -> Unit){

            itemView.carIcon.setImageResource(riderCarListModel.image)
            itemView.title.text = riderCarListModel.name

            itemView.setOnClickListener {
                clickListener.invoke(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectDriverViewHolder {
        return SelectDriverViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.taxi_ride,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = riderCarListModel.size

    override fun onBindViewHolder(holder: SelectDriverViewHolder, position: Int) {

        //for default check in first item
        if(position == 0)
        {
            //itemView.setBackgroundColor(Color.BLUE)
            lastCheckedPos = 0
        }
        return holder.bind(riderCarListModel[position], clickListener)
    }

}
