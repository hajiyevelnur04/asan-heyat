package com.eebros.asan.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eebros.asan.R
import com.eebros.asan.animateProgressImage
import com.eebros.asan.base.BaseFragment
import com.eebros.asan.di.ViewModelProviderFactory
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var storyRecyclerView: RecyclerView
    lateinit var asanProgress: ImageView

    private val storyList: ArrayList<Int> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        initView(root)

        animateProgressImage(asanProgress)

        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.HORIZONTAL
        storyRecyclerView.layoutManager = llm

        storyList.add(R.drawable.aze)
        storyList.add(R.drawable.rus)
        storyList.add(R.drawable.usa)

        storyList.add(R.drawable.aze)
        storyList.add(R.drawable.rus)
        storyList.add(R.drawable.usa)

        storyList.add(R.drawable.aze)
        storyList.add(R.drawable.rus)
        storyList.add(R.drawable.usa)


        val storyAdapter = StoriesRecyclerViewAdapter(storyList) {
                //val intent = Intent(requireActivity(), CampaignActivity::class.java)
                //intent.putExtra("campaignId", storyList[it].campaignId)
                //startActivity(intent)
            }
        storyRecyclerView.adapter = storyAdapter

        asanProgress.clearAnimation()
        asanProgress.visibility = View.GONE

        return root
    }

    private fun initView(root: View) {
        asanProgress = root.findViewById(R.id.asanProgress)
        storyRecyclerView = root.findViewById(R.id.storyRecyclerView)
    }


}
