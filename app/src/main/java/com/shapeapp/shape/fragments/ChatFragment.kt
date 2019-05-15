package com.shapeapp.shape.fragments


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shapeapp.shape.R
import com.shapeapp.shape.mockupsmakers.CardMockups
import com.shapeapp.shape.mockupsmakers.MessageMockups
import com.shapeapp.shape.recyclerviewadapters.MessagesFullRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.message_full_item.*


/**
 * Represents conversation with another user
 */
class ChatFragment : Fragment() {

    //  TODO: implement ViewModel

    private val arguments: ChatFragmentArgs by navArgs()

    private val messagesRecyclerViewAdapter = MessagesFullRecyclerViewAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedRecyclerViewAdapter()
    }

    private fun feedRecyclerViewAdapter() {
        //  TODO: load messages list from arguments
        messagesRecyclerViewAdapter.messagesDataset = MessageMockups.randomFullMessages
        messagesRecyclerViewAdapter.notifyDataSetChanged()
    }

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
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        messages_recyclerview.apply {
            layoutManager = linearLayoutManager
            adapter = messagesRecyclerViewAdapter
        }
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
