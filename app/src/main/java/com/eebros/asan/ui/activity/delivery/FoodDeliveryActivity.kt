package com.eebros.asan.ui.activity.delivery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eebros.asan.R
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.data.remote.response.RestaurantResponseModel
import com.eebros.asan.di.ViewModelProviderFactory
import com.eebros.asan.ui.activity.common.SortDialog
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.asan_toolbar_layout.*
import javax.inject.Inject


class FoodDeliveryActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: FoodDeliveryViewModel

    private lateinit var foodRecyclerView: RecyclerView
    private val foodList: ArrayList<RestaurantResponseModel> = arrayListOf()

    lateinit var productSort: LinearLayout
    lateinit var productFilter: LinearLayout

    lateinit var viewBottom: View

    lateinit var mShimmerViewContainer: ShimmerFrameLayout

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private lateinit var bottomSheet: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_delivery)

        viewModel = ViewModelProvider(this, factory)[FoodDeliveryViewModel::class.java]

        initView()

        toolbar_back_button.setOnClickListener{
            onBackPressed()
        }

        toolbar_title.text = getString(R.string.food_delivery)

        productSort.setOnClickListener{
            var list: Array<String> = arrayOf("Rating","Delivery time","Price Low to High", "Price High to Low")
            SortDialog(this).sortFunction("Sort By", list)
        }

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> { }
                    BottomSheetBehavior.STATE_EXPANDED -> { }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        viewBottom.visibility = View.GONE
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> { }
                    BottomSheetBehavior.STATE_SETTLING -> { }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                viewBottom.visibility = View.VISIBLE
                viewBottom.alpha = slideOffset
            }
        })

        viewBottom.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }

        productFilter.setOnClickListener{

            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }


        }

        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        foodRecyclerView.layoutManager = llm

        foodList.add(RestaurantResponseModel("McDonalds",
            4L, "20 min", "5 min order",
            "get 10% off an all order", true))
        foodList.add(RestaurantResponseModel("PizzaHut",
            3L, "25 min", "6 min order",
            "get 20% off an all order", false))
        foodList.add(RestaurantResponseModel("McDonalds",
            5L, "10 min", "7 min order",
            "get 30% off an all order", true))
        foodList.add(RestaurantResponseModel("McDonalds",
            2L, "15 min", "3 min order",
            "get 50% off an all order", true))
        foodList.add(RestaurantResponseModel("McDonalds",
            1L, "20 min", "1 min order",
            "get 60% off an all order", false))

        foodRecyclerView.visibility = View.VISIBLE
        val storyAdapter = FoodDeliveryAdapter(foodList) {
            startActivity(Intent(this, StoreDetailActivity::class.java))
        }
        foodRecyclerView.adapter = storyAdapter

        mShimmerViewContainer.stopShimmerAnimation()
        mShimmerViewContainer.visibility = View.GONE

    }

    private fun initView() {
        bottomSheet = findViewById(R.id.bottom_sheet_filter)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        foodRecyclerView = findViewById(R.id.foodRecyclerView)

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container)

        productSort = findViewById(R.id.product_sorting)

        productFilter = findViewById(R.id.product_filter)

        viewBottom = findViewById(R.id.view_bottom_sheet_bg)
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
