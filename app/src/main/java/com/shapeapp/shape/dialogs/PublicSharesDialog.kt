package com.shapeapp.shape.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
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
                .setCancelable(false)
            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")

        changeBackgroundToTransparent(createdDialog)
        changePositionToBottom(createdDialog)

        return createdDialog
    }

    /**
     * Set the background of the dialog's root view to transparent
     * See: https://stackoverflow.com/a/28937224
     */
    private fun changeBackgroundToTransparent(createdDialog: AlertDialog) {
        createdDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    /**
     * Make the [AlertDialog] appear at the bottom of the screen
     */
    private fun changePositionToBottom(createdDialog: AlertDialog) {
        createdDialog.window?.attributes?.gravity = Gravity.BOTTOM
    }
}