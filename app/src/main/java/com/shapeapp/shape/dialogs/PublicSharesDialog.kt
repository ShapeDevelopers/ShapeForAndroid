package com.shapeapp.shape.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.shapeapp.shape.R


class PublicSharesDialog : DialogFragment() {

    //  TODO: finish its layout and behaviour

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setView(R.layout.dialog_public_shares)
                .setMessage("This is message")
                .setPositiveButton("Positive", { dialog, id ->
                    //  DO SOMETHING
                })
                .setNegativeButton("Negative", { dialog, id ->
                    //  DO SOMETHING
                })

            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")
    }
}