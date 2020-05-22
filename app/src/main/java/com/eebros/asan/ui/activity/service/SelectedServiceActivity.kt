package com.eebros.asan.ui.activity.service

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eebros.asan.R
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import com.eebros.asan.model.ServicePersonModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.asan_toolbar_layout.*
import javax.inject.Inject

class SelectedServiceActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModelSelected: SelectedServiceViewModel

    private lateinit var serviceRecyclerView: RecyclerView
    private val serviceList: ArrayList<ServicePersonModel> = arrayListOf()

    lateinit var productSort: LinearLayout
    lateinit var productFilter: LinearLayout

    lateinit var viewBottom: View

    lateinit var mShimmerViewContainer: ShimmerFrameLayout

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private lateinit var bottomSheet: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_service)

        viewModelSelected = ViewModelProvider(this, factory)[SelectedServiceViewModel::class.java]

        initViews()

        toolbar_back_button.setOnClickListener{
            onBackPressed()
        }
        toolbar_title.text = getString(R.string.service_people)

        productSort.setOnClickListener{
            /*var list: Array<String> = arrayOf("Rating","Delivery time","Price Low to High", "Price High to Low")
            SortDialog(this).dialogCreate("Sort By", list)*/
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

            /*if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
*/

        }

        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        serviceRecyclerView.layoutManager = llm

        serviceList.add(ServicePersonModel("name","surname"))
        serviceList.add(ServicePersonModel("name","surname"))
        serviceList.add(ServicePersonModel("name","surname"))
        serviceList.add(ServicePersonModel("name","surname"))
        serviceList.add(ServicePersonModel("name","surname"))
        serviceList.add(ServicePersonModel("name","surname"))
        serviceList.add(ServicePersonModel("name","surname"))

        serviceRecyclerView.visibility = View.VISIBLE
        val serviceAdapter = SelectedServiceAdapter(serviceList) {
            //startActivity(Intent(this, StoreDetailActivity::class.java))
        }
        serviceRecyclerView.adapter = serviceAdapter

        mShimmerViewContainer.stopShimmerAnimation()
        mShimmerViewContainer.visibility = View.GONE
    }

    private fun initViews() {
        bottomSheet = findViewById(R.id.bottom_sheet_filter)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        serviceRecyclerView = findViewById(R.id.serviceRecyclerView)

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
