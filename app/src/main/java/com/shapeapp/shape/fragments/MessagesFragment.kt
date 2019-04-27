package com.shapeapp.shape.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shapeapp.shape.R
import kotlinx.android.synthetic.main.fragment_messages.*

//  TODO: complete messages loading

/**
 * A simple [Fragment] subclass.
 */
class MessagesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureButtons()
    }

    private fun configureButtons() {
        my_profile_button.setOnClickListener {
            loadProfileFragment()
        }
    }

    private fun loadProfileFragment() {
        findNavController().navigate(R.id.action_messagesFragment_to_profileFragment)
    }
}
