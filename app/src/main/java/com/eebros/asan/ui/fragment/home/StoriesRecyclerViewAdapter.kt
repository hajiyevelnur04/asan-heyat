package com.eebros.asan.ui.fragment.home

import android.content.Context
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eebros.asan.R
import kotlinx.android.synthetic.main.story_item.view.*

class StoriesRecyclerViewAdapter(
    private val storyList: ArrayList<Int>,
    private val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<StoriesRecyclerViewAdapter.StoriesViewHolder>(){

    class StoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(stories: Int, clickListener: (position: Int) -> Unit){
            with(stories) {
                //val byteArray = Base64.decode(stories, Base64.DEFAULT)
                Glide.with(itemView)
                    .load(stories)
                    .centerCrop()
                    .into(itemView.storyImage)

                itemView.setOnClickListener {
                    clickListener.invoke(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        return StoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.story_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = storyList.size

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        return holder.bind(storyList[position], clickListener)
    }

}