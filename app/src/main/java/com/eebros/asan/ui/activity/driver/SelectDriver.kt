package com.eebros.asan.ui.activity.driver

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.media.Image
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eebros.asan.R
import com.eebros.asan.model.RiderCarListModel
import com.eebros.asan.view.SpanningLinearLayoutManager
import com.skyfishjy.library.RippleBackground
import kotlinx.android.synthetic.main.activity_select_driver.*
import java.util.*


class SelectDriver : AppCompatActivity() {

    lateinit var riderCard: RecyclerView

    private val riderCarList: ArrayList<RiderCarListModel> = arrayListOf()

    lateinit var rideLater: TextView
    lateinit var rideNow: TextView

    lateinit var cardViewHolder: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_driver)

        riderCard = findViewById(R.id.rv_rideRider_cars)
        cardViewHolder = findViewById(R.id.car_rideRider_serviceLayout)

        rideLater = findViewById(R.id.btn_rideLater)
        rideNow = findViewById(R.id.btn_rideNow)

        val rippleBackground = findViewById<RippleBackground>(R.id.content)
        val imageView = findViewById<ImageView>(R.id.centerImage)
        val cancelView = findViewById<ImageView>(R.id.btn_cancel_driver_req)

        val sllm = SpanningLinearLayoutManager(this)
        sllm.orientation = LinearLayoutManager.HORIZONTAL
        riderCard.layoutManager = sllm

        riderCarList.add(RiderCarListModel(R.drawable.sedan, "Sedan"))
        riderCarList.add(RiderCarListModel(R.drawable.suv, "SUV"))
        riderCarList.add(RiderCarListModel(R.drawable.city_car, "City car"))
        riderCarList.add(RiderCarListModel(R.drawable.sportcar, "Luxury"))


        val riderCarAdapter = SelectDriverAdapter(riderCarList) {

        }
        riderCard.adapter = riderCarAdapter

        rideNow.setOnClickListener {
            rl_map_rider.isFocusable = false
            cardViewHolder.visibility = View.GONE
            rippleBackground.visibility = View.VISIBLE
            imageView.visibility = View.VISIBLE
            cancelView.visibility = View.VISIBLE
            rippleBackground.startRippleAnimation()
        }

        cancelView.setOnClickListener{
            rippleBackground.stopRippleAnimation()
            rl_map_rider.isFocusable = true
            cardViewHolder.visibility = View.VISIBLE
            rippleBackground.visibility = View.GONE
            imageView.visibility = View.GONE
            cancelView.visibility = View.GONE
        }

        rideLater.setOnClickListener {

            var calendar = Calendar.getInstance()
            var mYear = calendar.get(Calendar.YEAR)
            var mMonth = calendar.get(Calendar.MONTH)
            var mDay = calendar.get(Calendar.DAY_OF_MONTH)

            // get the current time
            var mHour = calendar.get(Calendar.HOUR_OF_DAY)
            var mMinute = calendar.get(Calendar.MINUTE)
            var mSecond = calendar.get(Calendar.SECOND)

            var timeT = StringBuilder()
                // Month is 0 based so add 1
                .append(mMonth + 1).append("-")
                .append(mDay).append("-")
                .append(mYear).append(" ")
                .append(mHour).append(" ")
                .append(mMinute).append(" ")

            Log.d("TAG", timeT.toString())

            val mTimeSetListener = OnTimeSetListener { _, hourOfDay, minute ->
                    mHour = hourOfDay
                    mMinute = minute
                    updateDisplay()
                }

            var timePickerDialog = TimePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar, mTimeSetListener, mHour, mMinute, true)
            timePickerDialog.setTitle("Schedule your ride")
            timePickerDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            timePickerDialog.show()

            /*val mDateSetListener =
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    mYear = year
                    mMonth = monthOfYear
                    mDay = dayOfMonth
                    updateDisplay()
                }

            DatePickerDialog(this,
                mDateSetListener,
                mYear, mMonth, mDay);*/
        }

    }

    private fun updateDisplay() {

    }

}
