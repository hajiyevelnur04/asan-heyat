package com.eebros.asan.ui.activity.registration

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.R
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import com.eebros.asan.ui.activity.PinActivity
import javax.inject.Inject


class NumberActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    var selectedCode: String = "+994"

    lateinit var itemCodeHolder: EditText
    lateinit var phoneNumberHolder: EditText
    lateinit var continueB: Button

    lateinit var itemCodeContainer: LinearLayout
    lateinit var phoneNumberContainer: LinearLayout

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)

        //init view model with factory
        viewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]

        initView()
        defaultButtonView()
        //setUpSpinner()
        setUpEditText()

        phoneNumberHolder.filters = arrayOf(InputFilter.LengthFilter(7))

        continueB.setOnClickListener{
            var code:String = itemCodeHolder.text.toString()
            var intent = Intent(this, PinActivity::class.java)
            intent.putExtra("phoneNum", "$selectedCode${phoneNumberHolder.text}")
            startActivity(intent)
        }
    }

    private fun initView(){
        itemCodeHolder = findViewById(R.id.itemCodeHolder)
        phoneNumberHolder = findViewById(R.id.phoneNumberHolder)

        itemCodeContainer = findViewById(R.id.itemCodeContainer)
        phoneNumberContainer = findViewById(R.id.phoneNumberContainer)

        continueB = findViewById(R.id.continueButton)
    }

    private fun defaultButtonView() {
        continueB.isEnabled = false
        continueB.isFocusable = false
        continueB.setBackgroundResource(R.drawable.rounded_corners_gray)
    }

    private fun setUpEditText() {
        phoneNumberHolder.addTextChangedListener(object : TextWatcher{
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

    /*private fun setUpSpinner() {

        val country: List<CountryCode> = arrayListOf(
            CountryCode("+994", R.drawable.aze),
            CountryCode("+7", R.drawable.rus),
            CountryCode("+1", R.drawable.usa))

        val adapter = CountryCodeAdapter(
            this,
            country
        )

        countryCodeSpinner.adapter = adapter

        countryCodeSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCode = country[position].code
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }*/

}
