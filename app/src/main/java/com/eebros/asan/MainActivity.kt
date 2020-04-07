package com.eebros.asan

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.adapter.CountryCodeAdapter
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import com.eebros.asan.model.CountryCode
import com.eebros.asan.ui.activity.PinActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    var countryNum : Long = 0L
    var selectedCode: String = ""

    lateinit var countryCode: Spinner
    lateinit var phoneNumber: EditText
    lateinit var continueB: Button

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init view model with factory
        viewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]

        initView()
        setUpSpinner()
        setUpEditText()

        continueB.setOnClickListener{
            var intent = Intent(this, PinActivity::class.java)
            intent.putExtra("phoneNum", "$selectedCode$phoneNumber")
            startActivity(intent)
        }
    }

    private fun initView(){
        countryCode = findViewById(R.id.countryCode)
        phoneNumber = findViewById(R.id.phoneNumber)
        continueB = findViewById(R.id.continueButton)
    }

    private fun setUpEditText() {
        phoneNumber.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().trim().isNotEmpty()){
                    continueB.isEnabled = true
                    continueB.isFocusable = true
                    continueB.setBackgroundResource(R.drawable.rounded_corners_primary)
                } else {
                    continueB.isEnabled = false
                    continueB.isFocusable = false
                    continueB.setBackgroundResource(R.drawable.rounded_corners_gray)
                }

            }
        })
    }

    private fun setUpSpinner() {

        val country: List<CountryCode> = arrayListOf(
            CountryCode("+994", R.drawable.aze),
            CountryCode("+7", R.drawable.rus),
            CountryCode("+1", R.drawable.usa))

        val adapter = CountryCodeAdapter(
            this,
            country
        )

        countryCode.adapter = adapter

        /*countryCode.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // set the selected item to variable
                selectedCode = country[position].code
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }*/
    }


}
