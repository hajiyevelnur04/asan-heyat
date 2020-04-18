package com.eebros.asan.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eebros.asan.R
import com.eebros.asan.animateProgressImage
import com.eebros.asan.base.BaseFragment
import com.eebros.asan.di.ViewModelProviderFactory
import com.eebros.asan.ui.activity.ComingSoonActivity
import com.eebros.asan.ui.activity.ComingSoonViewModel
import com.eebros.asan.ui.activity.delivery.FoodDeliveryActivity
import com.eebros.asan.ui.activity.driver.MapsActivity
import javax.inject.Inject


class HomeFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var storyRecyclerView: RecyclerView
    private lateinit var serviceGridView: GridView

    lateinit var asanStoryProgress: ImageView
    lateinit var asanServiceProgress: ImageView

    private val storyList: ArrayList<Int> = arrayListOf()

    private val serviceList: ArrayList<String> = arrayListOf()

    /*private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private lateinit var bottomSheet: LinearLayout

    lateinit var viewBottom: View*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        initView(root)

        animateProgressImage(arrayListOf(asanServiceProgress, asanStoryProgress))

        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.HORIZONTAL
        storyRecyclerView.layoutManager = llm


        //static values
        storyList.add(R.drawable.pizza)
        storyList.add(R.drawable.cleaning)
        storyList.add(R.drawable.barbar)

        storyList.add(R.drawable.santexnik)
        storyList.add(R.drawable.beverage)


        serviceList.add("taxi ride")
        serviceList.add("food delivery")
        serviceList.add("courier service")
        serviceList.add("home cleaning")


        serviceList.add("beauty services")
        serviceList.add("workout trainer")
        serviceList.add("flowers delivery")
        serviceList.add("water delivery")

        serviceList.add("baby care")
        serviceList.add("car wash")
        serviceList.add("electronics")
        serviceList.add("pest control")

        /*serviceList.add("bike ride")
        serviceList.add("flowers delivery")
        serviceList.add("courier service")
        serviceList.add("more")*/


        val storyAdapter = StoriesRecyclerViewAdapter(storyList) {
                val intent = Intent(requireActivity(), CampaignActivity::class.java)
                intent.putExtra("campaignId", storyList[it])
                startActivity(intent)
            }
        storyRecyclerView.adapter = storyAdapter

        asanStoryProgress.clearAnimation()
        asanStoryProgress.visibility = View.GONE


        val serviceAdapter = ServiceRecyclerViewAdapter(requireContext(), serviceList) {
            when(it){
                0 -> {
                    startActivity(Intent(requireActivity(), MapsActivity::class.java))
                    requireActivity().overridePendingTransition(R.anim.enter, R.anim.exit)
                }

                1 -> {
                    startActivity(Intent(requireActivity(), FoodDeliveryActivity::class.java))
                    requireActivity().overridePendingTransition(R.anim.enter, R.anim.exit)
                }
                else -> {
                    startActivity(Intent(requireActivity(), ComingSoonActivity::class.java))
                    requireActivity().overridePendingTransition(R.anim.enter, R.anim.exit)
                }
            }

            /*if (bottomSheetBehavior!!.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
            }*/
        }
        serviceGridView.adapter = serviceAdapter

        asanServiceProgress.clearAnimation()
        asanServiceProgress.visibility = View.GONE

        /*bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
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
            *//*bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)*//*
        }*/

        return root
    }

    private fun initView(root: View) {
        asanStoryProgress = root.findViewById(R.id.asanStoryProgress)
        asanServiceProgress = root.findViewById(R.id.asanServiceProgress)
        serviceGridView = root.findViewById(R.id.serviceGridView)
        storyRecyclerView = root.findViewById(R.id.storyRecyclerView)
        /*viewBottom = root.findViewById(R.id.view_bottom_sheet_bg)
        bottomSheet = root.findViewById(R.id.bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)*/
    }

    /*private fun openBottomSheet() {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun closeBottomSheet() {
        if (bottomSheetBehavior != null && bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }*/

}
