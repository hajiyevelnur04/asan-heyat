package com.eebros.asan.ui.fragment.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.eebros.asan.R
import com.eebros.asan.data.local.ServiceImageMapper
import kotlinx.android.synthetic.main.service_item.view.*

class ServiceRecyclerViewAdapter(
    context: Context,
    private val serviceList: ArrayList<String>,
    private val clickListener: (position: Int) -> Unit
) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{
        val view = inflater.inflate(R.layout.service_item, parent, false)

        view.firstImage.setImageResource(ServiceImageMapper().getCategoryImage(serviceList[position]))
        view.firstText.text = serviceList[position]

        view.setOnClickListener { clickListener.invoke(position) }

        return view
    }

    override fun getItem(position: Int) = null

    override fun getItemId(position: Int) = 0L

    override fun getCount() = serviceList.size
}