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
import kotlinx.android.synthetic.main.activity_verify_phone_number.*
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

        serviceList.add("asan təmizlik")
        serviceList.add("asan santexnik")
        serviceList.add("asan bərbər")
        serviceList.add("asan çatdirilma")

        serviceList.add("asan təmizlik")
        serviceList.add("asan santexnik")
        serviceList.add("asan bərbər")
        serviceList.add("asan çatdirilma")


        val storyAdapter = StoriesRecyclerViewAdapter(storyList) {
                val intent = Intent(requireActivity(), CampaignActivity::class.java)
                intent.putExtra("campaignId", storyList[it])
                startActivity(intent)
            }
        storyRecyclerView.adapter = storyAdapter

        asanStoryProgress.clearAnimation()
        asanStoryProgress.visibility = View.GONE


        val serviceAdapter = ServiceRecyclerViewAdapter(requireContext(), serviceList) {

        }
        serviceGridView.adapter = serviceAdapter

        asanServiceProgress.clearAnimation()
        asanServiceProgress.visibility = View.GONE

        return root
    }

    private fun initView(root: View) {
        asanStoryProgress = root.findViewById(R.id.asanStoryProgress)
        asanServiceProgress = root.findViewById(R.id.asanServiceProgress)
        serviceGridView = root.findViewById(R.id.serviceGridView)
        storyRecyclerView = root.findViewById(R.id.storyRecyclerView)
    }


}
