package com.shapeapp.shape.ui

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.shapeapp.shape.R
import com.shapeapp.shape.ui.ProfileFragmentArgs
import kotlinx.android.synthetic.main.fragment_profile.*

//  TODO: check and implement loading data from source (MVVM)

/**
 * Shows info about user's profile and friends-related options.
 */
class ProfileFragment : Fragment() {

    private val arguments: ProfileFragmentArgs by navArgs()

    private val avatarAnimator by lazy { generateAvatarAnimator() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avatar_circularimageview.setOnLongClickListener {
            controlAvatarAnimator()
            true
        }

        loadArgumentsToUi()
    }

    private fun loadArgumentsToUi() {
        val user = arguments.user
        user.run {
            avatar_circularimageview.setImageURI(Uri.parse(avatarUri))
            nickname_textview.text = nickname
            name_textview.text = name
            surname_textview.text = surname
            sex_textview.text = sex
            email_textview.text = email
            birth_date_textview.text = birthDate
            //  TODO: consider min/max for progressBar
            distance_progressBar.progress = radarRadius.toInt()
            //  TODO: use resource string with placeholders
            distance_value_textview.text = "$radarRadius km"
        }
    }

    private fun controlAvatarAnimator() {
        when (avatarAnimator.isStarted) {
            true -> avatarAnimator.end()
            false -> avatarAnimator.start()
        }
    }

    private fun generateAvatarAnimator(): ObjectAnimator {
        val scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(
            avatar_circularimageview,
            PropertyValuesHolder.ofFloat("scaleX", 1.1f),
            PropertyValuesHolder.ofFloat("scaleY", 1.1f)
        )
        scaleAnimator.apply {
            duration = 400
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }
        return scaleAnimator
    }

}
