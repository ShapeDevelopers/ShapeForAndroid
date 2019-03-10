package com.shapeapp.shape.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.shapeapp.shape.R


class PublicSharesDialog : DialogFragment() {

    //  TODO: finish its layout and behaviour
    //  TODO: include RecyclerView + Repo (?)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val createdDialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setView(R.layout.dialog_public_shares)
                .setMessage("My public shares")
                .setPositiveButton("YES") { dialog, id ->
                    //  DO SOMETHING
                }
                .setNegativeButton("NO") { dialog, id ->
                    //  DO SOMETHING
                }

            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")

        //  Set the background of the dialog's root view to transparent
        //  See: https://stackoverflow.com/a/28937224
        createdDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return createdDialog
    }
}