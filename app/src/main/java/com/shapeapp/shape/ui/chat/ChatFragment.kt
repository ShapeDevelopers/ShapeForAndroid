package com.shapeapp.shape.ui.chat


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shapeapp.shape.R
import com.shapeapp.shape.ui.recyclerviews.adapters.MessagesFullRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_chat.*


/**
 * Represents conversation with another user
 */
class ChatFragment : Fragment() {

    //  TODO: implement ViewModel

    private val messagesRecyclerViewAdapter = MessagesFullRecyclerViewAdapter(emptyList())
    private val arguments: ChatFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedRecyclerViewAdapter()
    }

    private fun feedRecyclerViewAdapter() {
        val messages = arguments.messages.toList()
        messagesRecyclerViewAdapter.messagesDataset = messages
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
        configureRecyclerView()
        scrollRecyclerView()
    }

    private fun configureRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        messages_recyclerview.apply {
            layoutManager = linearLayoutManager
            adapter = messagesRecyclerViewAdapter
        }
    }

    private fun scrollRecyclerView() {
        val bottomPosition = messagesRecyclerViewAdapter.messagesDataset.size - 1
        messages_recyclerview.scrollToPosition(bottomPosition)
    }

}
