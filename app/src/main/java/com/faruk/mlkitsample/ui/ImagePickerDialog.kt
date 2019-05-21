package com.faruk.mlkitsample.ui

/**
 * Created by farukhossain on 2019/05/20.
 */
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.faruk.mlkitsample.R


class ImagePickerDialog : DialogFragment() {

    private var listener: Listener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context!!)
                .setTitle(R.string.image_picker_dialog_title)
                .setMessage(R.string.image_picker_dialog_message)
                .setPositiveButton(R.string.image_picker_dialog_button_camera, { _, _ ->
                    listener?.onCameraSelected()
                })
                .setNegativeButton(R.string.image_picker_dialog_button_gallery, { _, _ ->
                    listener?.onGallerySelected()
                })
                .setCancelable(true)
                .create()
    }

    companion object {
        val TAG = ImagePickerDialog::class.java.simpleName
    }

    interface Listener {
        fun onCameraSelected()
        fun onGallerySelected()
    }
}