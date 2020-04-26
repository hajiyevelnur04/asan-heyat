package com.eebros.asan.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
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
import com.eebros.asan.ui.activity.service.ServiceActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import javax.inject.Inject


class HomeFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var storyRecyclerView: RecyclerView
    private lateinit var serviceGridView: GridView
    lateinit var bottomGridView: GridView

    lateinit var asanStoryProgress: ImageView
    lateinit var asanServiceProgress: ImageView

    private val storyList: ArrayList<Int> = arrayListOf()

    private val mainServiceList: ArrayList<String> = arrayListOf()
    private val bottomServiceList: ArrayList<String> = arrayListOf()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private lateinit var bottomSheet: LinearLayout

    lateinit var viewBottom: View

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

        mainServiceList.add("taxi ride")
        mainServiceList.add("food delivery")
        mainServiceList.add("courier service")
        mainServiceList.add("home cleaning")

        mainServiceList.add("workout trainer")
        mainServiceList.add("flowers delivery")
        mainServiceList.add("water delivery")
        mainServiceList.add("more")



        bottomServiceList.add("water delivery")
        bottomServiceList.add("food delivery")
        bottomServiceList.add("courier service")
        bottomServiceList.add("home cleaning")


        bottomServiceList.add("beauty services")
        bottomServiceList.add("workout trainer")
        bottomServiceList.add("flowers delivery")
        bottomServiceList.add("taxi ride")


        bottomServiceList.add("baby care")
        bottomServiceList.add("car wash")
        bottomServiceList.add("electronics")
        bottomServiceList.add("pest control")

        bottomServiceList.add("baby care")
        bottomServiceList.add("pest control")
        bottomServiceList.add("car repair")
        bottomServiceList.add("dog walking")

        val storyAdapter = StoriesRecyclerViewAdapter(storyList) {
                val intent = Intent(requireActivity(), CampaignActivity::class.java)
                intent.putExtra("campaignId", storyList[it])
                startActivity(intent)
            }
        storyRecyclerView.adapter = storyAdapter

        asanStoryProgress.clearAnimation()
        asanStoryProgress.visibility = View.GONE


        val mainServiceAdapter = ServiceRecyclerViewAdapter(requireContext(), mainServiceList) {
            when(it){
                0 -> {
                    startActivity(Intent(requireActivity(), MapsActivity::class.java))
                    requireActivity().overridePendingTransition(R.anim.enter, R.anim.exit)
                }

                1 -> {
                    startActivity(Intent(requireActivity(), FoodDeliveryActivity::class.java))
                    requireActivity().overridePendingTransition(R.anim.enter, R.anim.exit)
                }
                //home cleaning
                3 -> {
                    startActivity(Intent(requireActivity(), ServiceActivity::class.java))
                    requireActivity().overridePendingTransition(R.anim.enter, R.anim.exit)
                }
                7 -> {

                    if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
                    } else {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
                    }
                }
                else -> {
                    startActivity(Intent(requireActivity(), ComingSoonActivity::class.java))
                    requireActivity().overridePendingTransition(R.anim.enter, R.anim.exit)
                }
            }

        }


        val bottomServiceAdapter = ServiceRecyclerViewAdapter(requireContext(), bottomServiceList) {
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

        }
        serviceGridView.adapter = mainServiceAdapter
        bottomGridView.adapter = bottomServiceAdapter

        asanServiceProgress.clearAnimation()
        asanServiceProgress.visibility = View.GONE

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

        return root
    }

    private fun initView(root: View) {
        asanStoryProgress = root.findViewById(R.id.asanStoryProgress)
        asanServiceProgress = root.findViewById(R.id.asanServiceProgress)
        serviceGridView = root.findViewById(R.id.serviceGridView)
        bottomGridView = root.findViewById(R.id.bottomGridView)
        storyRecyclerView = root.findViewById(R.id.storyRecyclerView)
        viewBottom = root.findViewById(R.id.view_bottom_sheet_bg)
        bottomSheet = root.findViewById(R.id.bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
    }
}
