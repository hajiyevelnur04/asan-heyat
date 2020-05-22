package com.eebros.asan.ui.activity.pin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.eebros.asan.R
import com.eebros.asan.ui.activity.common.NumberKeyboard
import com.eebros.asan.ui.activity.common.NumberKeyboardListener
import java.text.NumberFormat

class Main2Activity : AppCompatActivity(), NumberKeyboardListener {

    private lateinit var amountEditText: TextView
    private var amount: Int = 0
    private val nf = NumberFormat.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        title = "Keyboard integer"
        amountEditText = findViewById(R.id.amount)
        val numberKeyboard = findViewById<NumberKeyboard>(R.id.numberKeyboard)
        numberKeyboard.setListener(this)
    }

    override fun onNumberClicked(number: Int) {
        val newAmount = (amount * 10.0 + number).toInt()
        if (newAmount <= MAX_ALLOWED_AMOUNT) {
            amount = newAmount
            showAmount()
        }
    }

    override fun onLeftAuxButtonClicked() {
        // Nothing to do
    }

    override fun onRightAuxButtonClicked() {
        amount = (amount / 10.0).toInt()
        showAmount()
    }

    private fun showAmount() {
        amountEditText.text = nf.format(amount.toLong())
    }

    companion object {
        private const val MAX_ALLOWED_AMOUNT = 99999
    }
}