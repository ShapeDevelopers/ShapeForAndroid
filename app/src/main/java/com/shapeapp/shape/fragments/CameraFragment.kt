package com.shapeapp.shape.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shapeapp.shape.R
import io.fotoapparat.Fotoapparat
import io.fotoapparat.parameter.ScaleType
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
    }

    private fun initCamera() {
        fotoapparat = Fotoapparat(
            context = context!!,
            view = camera_cameraview,
            scaleType = ScaleType.CenterCrop
        )
    }


}
