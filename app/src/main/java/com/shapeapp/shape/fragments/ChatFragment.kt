package com.shapeapp.shape.fragments


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.shapeapp.shape.R
import com.shapeapp.shape.mockupsmakers.CardMockups
import kotlinx.android.synthetic.main.fragment_chat.*


/**
 * Represents conversation with another user
 */
class ChatFragment : Fragment() {

    //  TODO: implement ViewModel

    private val arguments: ChatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMessage()
    }

    private fun loadMessage() {
        val senderName = arguments.message.senderNickname
        //  TODO: load real avatar based on nickname (?)
        val senderAvatarUri = Uri.parse(CardMockups.getRandomDrawableUriString())
        val text = arguments.message.textContent
        val fullDate = arguments.message.dateStampFull
        sender_nickname_textview.text = senderName
        sender_avatar_imageview.setImageURI(senderAvatarUri)
        text_textview.text = text
        full_date_textview.text = fullDate
    }

}
