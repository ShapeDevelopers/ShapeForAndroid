package com.shapeapp.shape.fragments


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.shapeapp.shape.R
import io.fotoapparat.Fotoapparat
import io.fotoapparat.parameter.ScaleType
import io.fotoapparat.result.BitmapPhoto
import kotlinx.android.synthetic.main.fragment_camera.*

/**
 * Provides in-app photo capturing.
 */
class CameraFragment : Fragment() {

    //  TODO: implement MVVM
    //  TODO: save photo to persistent storage

    companion object {
        /**
         * Camera permission request code, to match with code in [onRequestPermissionsResult]
         */
        private const val CAMERA_PERMISSION_REQUEST_CODE = 111
    }

    private var fotoapparat: Fotoapparat? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureCaptureBehaviour()
        configureCaptureButton()
        configurePhotoContainer()
    }

    private fun configureCaptureBehaviour() {
        when (isCameraPermissionAlreadyGranted()) {
            true -> initCamera()
            false -> requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        }
    }

    private fun isCameraPermissionAlreadyGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Handles permissions request response.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                when (wasUserGrantedPermission(grantResults)) {
                    true -> {
                        initCamera()
                        Toast.makeText(context, "User has accepted permission", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        Toast.makeText(context, "User hasn't accepted permission", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun wasUserGrantedPermission(grantResults: IntArray): Boolean {
        return grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    private fun initCamera() {
        fotoapparat = Fotoapparat(
            context = context ?: throw Exception("Invalid context"),
            view = preview_cameraview,
            scaleType = ScaleType.CenterCrop
        )
        fotoapparat?.start()
    }

    private fun configureCaptureButton() {
        capture_fab.setOnClickListener {
            takePicture()
        }
    }

    private fun takePicture() {
        val photoResult = fotoapparat?.takePicture()
        // Asynchronously converts photo to bitmap and returns the result on the main thread
        photoResult?.let {
            it.toBitmap()
                .whenAvailable { bitmapPhoto ->
                    photo_container_imageview.setImageBitmap(bitmapPhoto?.bitmap)
                    val rotationDeg = determineRotation(bitmapPhoto)
                    photo_container_imageview.rotation = rotationDeg
                    photo_container_imageview.visibility = View.VISIBLE
                }
        }
    }

    /**
     * Determines right rotation for view after taking photo with [Fotoapparat]
     */
    private fun determineRotation(bitmapPhoto: BitmapPhoto?): Float {
        val relativeRotationDeg = bitmapPhoto?.rotationDegrees ?: 0
        val relativeRotationDegFloat = relativeRotationDeg.toFloat()
        return -relativeRotationDegFloat
    }

    private fun configurePhotoContainer() {
        //  TODO: provide further actions with photo
        photo_container_imageview.setOnClickListener {
            Toast.makeText(context, "Your photo was saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        fotoapparat?.start()
    }

    override fun onStop() {
        super.onStop()
        fotoapparat?.stop()
    }

}
