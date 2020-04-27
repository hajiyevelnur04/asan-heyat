package com.eebros.asan.ui.fragment.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.bumptech.glide.Glide
import com.eebros.asan.R
import kotlinx.android.synthetic.main.activity_campaign.*

class CampaignActivity : AppCompatActivity() {

    private val storyList: ArrayList<Int> = arrayListOf()

    private val campaignId: Int by lazy { intent.getIntExtra("campaignId", 0) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign)

        storyList.add(R.drawable.pizza)
        storyList.add(R.drawable.cleaning)
        storyList.add(R.drawable.barbar)

        storyList.add(R.drawable.santexnik)
        storyList.add(R.drawable.beverage)


        var campaignPosition = 0
        for (i in 0 until storyList.size) {
            if (campaignId == i) {
                Glide
                    .with(this)
                    .load(storyList[i])
                    .into(campaignImage)

                campaignPosition = i
                break
            }
        }

        leftPortion.setOnClickListener {
            if (campaignPosition - 1 >= 0) {
                campaignPosition -= 1

                Glide
                    .with(this)
                    .load(storyList[campaignPosition])
                    .into(campaignImage)

            }
        }

        rightPortion.setOnClickListener {
            if (campaignPosition + 1 <= storyList.size - 1) {
                campaignPosition += 1
                /*val byteArray =
                    Base64.decode(campaignList[campaignPosition].image, Base64.DEFAULT)*/
                Glide
                    .with(this)
                    .load(storyList[campaignPosition])
                    .into(campaignImage)
            }
        }
    }
}
