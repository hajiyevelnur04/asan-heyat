package com.eebros.asan.ui.activity.delivery

import android.R.attr.x
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eebros.asan.R
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.asan_toolbar_layout.*


class FoodDelivery : AppCompatActivity() {

    private lateinit var foodRecyclerView: RecyclerView
    private val foodList: ArrayList<Int> = arrayListOf()

    lateinit var mShimmerViewContainer: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_delivery)

        foodRecyclerView = findViewById(R.id.foodRecyclerView)

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container)

        toolbar_back_button.setOnClickListener{
            onBackPressed()
        }

        toolbar_title.text = getString(R.string.food_delivery)

        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        foodRecyclerView.layoutManager = llm

        foodList.add(0)
        foodList.add(0)
        foodList.add(0)
        foodList.add(0)
        foodList.add(0)
        foodList.add(0)

        foodRecyclerView.visibility = View.VISIBLE
        val storyAdapter = FoodDeliveryAdapter(foodList) {

        }
        foodRecyclerView.adapter = storyAdapter

        mShimmerViewContainer.stopShimmerAnimation()
        mShimmerViewContainer.visibility = View.GONE

    }

    override fun onPause() {
        super.onPause()
        mShimmerViewContainer.stopShimmerAnimation();
    }

    override fun onResume() {
        super.onResume()
        mShimmerViewContainer.startShimmerAnimation();
    }
}
