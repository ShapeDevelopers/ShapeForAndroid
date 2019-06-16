package com.shapeapp.shape.ui.takephoto

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shapeapp.shape.R
import com.shapeapp.shape.internal.constants.Authorities
import com.shapeapp.shape.internal.filehandlers.FileOperator
import kotlinx.android.synthetic.main.fragment_take_photo.*
import java.io.File
import java.io.IOException

// TODO: delete, it was replaced by [CameraFragment]

/**
 * Takes photo.
 *
 * A simple [Fragment] subclass.
 * Use the [TakePhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TakePhotoFragment : Fragment() {

    private lateinit var viewModel: TakePhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TakePhotoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_take_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        connectUiToViewModel()
        configureTakePictureButton()
    }

    private fun connectUiToViewModel() {
        viewModel.let {
            it.photoUri.observe(this, Observer { photoUri ->
                loadPhotoIntoUI(photoUri)
            })
            it.isCameraIconVisible.observe(this, Observer { isCameraIconVisible ->
                camera_icon_imageview.changeVisibility(isCameraIconVisible)
            })
            it.isTakenPhotoVisible.observe(this, Observer { isTakenPhotoVisible ->
                taken_photo_imageview.changeVisibility(isTakenPhotoVisible)
            })
        }
    }

    private fun loadPhotoIntoUI(photoUri: Uri?) {
        taken_photo_imageview.setImageURI(photoUri)
    }

    /**
     * Extension function for [View] to change visibility based on [Boolean]
     */
    private fun View.changeVisibility(shouldBeVisible: Boolean) {
        when (shouldBeVisible) {
            true -> this.visibility = View.VISIBLE
            false -> this.visibility = View.INVISIBLE
        }
    }

    private fun configureTakePictureButton() {
        take_photo_button.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    /**
     * Dispatches [Intent] to capture image from camera.
     * After taking photo, [onActivityResult] is invoked
     */
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        activity?.let {
            takePictureIntent.resolveActivity(it.packageManager)?.let {
                val photoFile = tryToCreateImageFile()
                photoFile?.let {
                    context?.let { context ->
                        val photoFileUri =
                            FileProvider.getUriForFile(context, Authorities.FILE_PROVIDER_AUTHORITY, photoFile)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFileUri)
                        startActivityForResult(takePictureIntent,
                            REQUEST_IMAGE_CAPTURE
                        )
                        viewModel.startedWaitingForPhoto(photoFileUri)
                    }
                }
            }
        }
    }

    private fun tryToCreateImageFile(): File? {
        return try {
            context?.let {
                FileOperator.createImageFile(it)
            }
        } catch (e: IOException) {
            Log.e(this.javaClass.simpleName, "Error occurred when trying to create image file")
            null
        }
    }

    /**
     * Invoked when result from camera is coming back.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            viewModel.finishedWaitingForPhoto()
        }
    }


    companion object {

        private const val REQUEST_IMAGE_CAPTURE = 1

        /**
         * Use this factory method to create a new instance of
         * this fragment
         *
         * @return A new instance of fragment TakePhotoFragment.
         */
        @JvmStatic
        fun newInstance() = TakePhotoFragment()
    }
}
