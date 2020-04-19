package com.eebros.asan.ui.activity.delivery

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eebros.asan.R
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.asan_toolbar_layout.*
import javax.inject.Inject


class FoodDeliveryActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: FoodDeliveryViewModel

    private lateinit var foodRecyclerView: RecyclerView
    private val foodList: ArrayList<Int> = arrayListOf()

    lateinit var productSort: LinearLayout
    lateinit var productFilter: LinearLayout

    lateinit var mShimmerViewContainer: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_delivery)

        viewModel = ViewModelProvider(this, factory)[FoodDeliveryViewModel::class.java]

        foodRecyclerView = findViewById(R.id.foodRecyclerView)

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container)

        productSort = findViewById(R.id.product_sorting)

        productFilter = findViewById(R.id.product_filter)

        toolbar_back_button.setOnClickListener{
            onBackPressed()
        }

        toolbar_title.text = getString(R.string.food_delivery)

        productSort.setOnClickListener{

        }

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
