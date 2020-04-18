package com.eebros.asan.ui.activity.common

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import com.eebros.asan.R

open class RiderDialog(private val context: Context) {

    var isEnabledImage: Boolean = false

    fun isEnabled() = isEnabledImage

    open fun dialogCreate(title: String, msg: String, imageButton: ImageButton) {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.rider_dialog, null)
        //show dialog
        val mAlertDialog = AlertDialog.Builder(context).setView(mDialogView).setCancelable(false).show()

        val dialogTitle: TextView = mDialogView.findViewById(R.id.tv_dialog_title)
        val dialogMessage: TextView = mDialogView.findViewById(R.id.tv_dialog_message)

        val dialogSubmit: TextView = mDialogView.findViewById(R.id.btn_dialogSubmit)
        val dialogCancel: TextView = mDialogView.findViewById(R.id.btn_dialogCancel)

        dialogTitle.text = title
        dialogMessage.text = msg

        dialogSubmit.setOnClickListener {
            imageButton.setBackgroundResource(R.drawable.rider_round_bg_selected)
            isEnabledImage = true
            mAlertDialog.dismiss()
        }

        dialogCancel.setOnClickListener {
            imageButton.setBackgroundResource(R.drawable.rider_round_bg_white)
            isEnabledImage = false
            mAlertDialog.dismiss()
        }

    }
}