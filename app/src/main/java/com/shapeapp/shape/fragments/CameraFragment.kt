package com.shapeapp.shape.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shapeapp.shape.R
import io.fotoapparat.Fotoapparat
import io.fotoapparat.parameter.ScaleType
import io.fotoapparat.result.BitmapPhoto
import kotlinx.android.synthetic.main.fragment_camera.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CameraFragment : Fragment() {

    private lateinit var fotoapparat: Fotoapparat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCamera()
        configureCaptureButton()
    }

    private fun initCamera() {
        fotoapparat = Fotoapparat(
            context = context!!,
            view = preview_cameraview,
            scaleType = ScaleType.CenterCrop
        )
    }

    private fun configureCaptureButton() {
        capture_fab.setOnClickListener {
            takePicture()
        }
    }

    private fun takePicture() {
        val photoResult = fotoapparat.takePicture()
        // Asynchronously converts photo to bitmap and returns the result on the main thread
        photoResult
            .toBitmap()
            .whenAvailable { bitmapPhoto ->
                photo_container_imageview.setImageBitmap(bitmapPhoto?.bitmap)
                val rotationDeg = determineRotation(bitmapPhoto)
                photo_container_imageview.rotation = rotationDeg
                photo_container_imageview.visibility = View.VISIBLE
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

    override fun onStart() {
        super.onStart()
        fotoapparat.start()
    }

    override fun onStop() {
        super.onStop()
        fotoapparat.stop()
    }

}
