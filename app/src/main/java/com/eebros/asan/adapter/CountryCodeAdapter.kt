package com.eebros.asan.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.eebros.asan.R
import com.eebros.asan.model.CountryCode
import kotlinx.android.synthetic.main.country_code_spinner_item.view.*

class CountryCodeAdapter(ctx: Context, codes: List<CountryCode>) : ArrayAdapter<CountryCode>(ctx, 0, codes) {
    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }
    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }
    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val item = getItem(position)
        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.country_code_spinner_item,
            parent,
            false
        )
        view.countryCodeImage.setImageResource(item!!.image)
        view.countryCodeText.text = item?.code
        return view
    }
}