package com.eebros.asan.ui.activity.common

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ListView
import kotlinx.android.synthetic.main.number_board.*


open class SortDialog(private val context: Context) {

    open fun dialogCreate(title: String, list: Array<String>) {

        val mBuilder = AlertDialog.Builder(context)

        mBuilder.setTitle(title)
        var position: Int = 0
        mBuilder.setSingleChoiceItems(list, position) { dialogInterface, i ->
            position = i
            dialogInterface.dismiss()
        }
        // Set the neutral/cancel button click listener
        mBuilder.setNeutralButton("Cancel") { dialog, _ ->
            // Do something when click the neutral button
            dialog.cancel()
        }

        var alertDialogObject: AlertDialog = mBuilder.create()

        var listView: ListView = alertDialogObject.listView
        listView.divider = ColorDrawable(Color.WHITE) // set color
        listView.dividerHeight = 2 // set height

        alertDialogObject.setOnShowListener{
            alertDialogObject.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLUE)
        }


        alertDialogObject.show()
    }
}