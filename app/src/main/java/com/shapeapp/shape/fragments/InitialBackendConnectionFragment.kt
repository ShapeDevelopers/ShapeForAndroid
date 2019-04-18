package com.shapeapp.shape.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shapeapp.shape.R

/**
 * A simple [Fragment] subclass.
 *
 * Use the [InitialBackendConnectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class InitialBackendConnectionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_initial_backend_connection, container, false)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment
         *
         * @return A new instance of fragment InitialBackendConnectionFragment.
         */
        @JvmStatic
        fun newInstance() = InitialBackendConnectionFragment()
    }
}
