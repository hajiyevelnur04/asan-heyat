package com.eebros.asan

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    var countryNum : Long = 0L

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init view model with factory
        viewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]

        initSpinner()
        initEditText()

        continueButton.setOnClickListener{
            var intent = Intent(this, PinActivity::class.java)
            intent.putExtra("phoneNum", "$countryNum$phoneNumber")
            startActivity(intent)
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }
    }

    private fun initEditText() {
        phoneNumber.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().trim().isNotEmpty()){
                    continueButton.isEnabled = true
                    continueButton.isFocusable = true
                    continueButton.setBackgroundResource(R.color.hint)
                } else {
                    continueButton.isEnabled = false
                    continueButton.isFocusable = false
                    continueButton.setBackgroundResource(R.color.colorPrimary)
                }

            }
        })
    }

    private fun initSpinner() {
        // Initializing a country code
        val country = arrayOf(+994, +7, +90, +49)
        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            country // Array
        )
        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        countryCode.adapter = adapter

        // Set an on item selected listener for spinner object
        countryCode.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // set the selected item to variable
                countryNum = parent.getItemIdAtPosition(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }
    }


}
