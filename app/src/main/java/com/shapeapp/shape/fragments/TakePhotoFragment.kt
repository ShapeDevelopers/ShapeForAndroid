package com.shapeapp.shape.fragments

import android.app.Activity.RESULT_OK
import android.content.Context
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
import com.shapeapp.shape.R
import com.shapeapp.shape.filehandlers.FileOperator
import kotlinx.android.synthetic.main.fragment_take_photo.*
import java.io.File
import java.io.IOException

//  TODO: clean-up whole file
//  TODO: transform to MVVM

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TakePhotoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TakePhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TakePhotoFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    /**
     * Holds [Uri] to taken photo
     */
    private var photoFileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        dispatchTakePictureIntent()
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
                        photoFileUri = FileProvider.getUriForFile(context, "com.shapeapp.fileprovider", photoFile)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFileUri)
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
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
     * Loads photo to UI after user finishes taking it.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            loadPhotoIntoUI()
        }
    }

    private fun loadPhotoIntoUI() {
        taken_photo_imageview.setImageURI(photoFileUri)
        taken_photo_imageview.visibility = View.VISIBLE
        camera_icon_imageview.visibility = View.INVISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_take_photo, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        private const val REQUEST_IMAGE_CAPTURE = 1

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TakePhotoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TakePhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
