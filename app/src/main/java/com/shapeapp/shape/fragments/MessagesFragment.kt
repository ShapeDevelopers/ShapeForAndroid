package com.shapeapp.shape.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shapeapp.shape.R
import com.shapeapp.shape.data.Message
import com.shapeapp.shape.recyclerviewadapters.MessagesPreviewRecyclerViewAdapter
import com.shapeapp.shape.recyclerviewinterfaces.RecyclerViewMessageClickListener
import com.shapeapp.shape.repositories.MessagesRepository
import com.shapeapp.shape.viewmodels.MessagesFragmentViewModel
import com.shapeapp.shape.viewmodels.MessagesFragmentViewModelFactory
import kotlinx.android.synthetic.main.fragment_messages.*

//  TODO: complete messages loading

/**
 * Shows sent, received and latest messages.
 */
class MessagesFragment : Fragment() {

    private lateinit var viewModel: MessagesFragmentViewModel

    private val sentMessagesRecyclerViewAdapter = MessagesPreviewRecyclerViewAdapter(emptyList())
    private val receivedMessagesRecyclerViewAdapter = MessagesPreviewRecyclerViewAdapter(emptyList())
    private val latestMessagesRecyclerViewAdapter = MessagesPreviewRecyclerViewAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        obtainViewModel()
        feedRecyclerViewAdapters()
        setListenersForRecyclerViewAdapters()
    }

    private fun obtainViewModel() {
        val messagesRepository = MessagesRepository
        val viewModelFactory = MessagesFragmentViewModelFactory(messagesRepository)
        viewModel =
            activity?.run { ViewModelProviders.of(this, viewModelFactory).get(MessagesFragmentViewModel::class.java) }
                ?: throw Exception("Invalid Activity")
    }

    private fun feedRecyclerViewAdapters() {
        //  for sent
        viewModel.sentMessages.observe(this, Observer { messages ->
            sentMessagesRecyclerViewAdapter.messagesDataset = messages
            sentMessagesRecyclerViewAdapter.notifyDataSetChanged()
        })
        //  for received
        viewModel.receivedMessages.observe(this, Observer { messages ->
            receivedMessagesRecyclerViewAdapter.messagesDataset = messages
            receivedMessagesRecyclerViewAdapter.notifyDataSetChanged()
        })
        //  for latest
        viewModel.latestMessages.observe(this, Observer { messages ->
            latestMessagesRecyclerViewAdapter.messagesDataset = messages
            latestMessagesRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    private fun setListenersForRecyclerViewAdapters() {
        val messageClickListener = object : RecyclerViewMessageClickListener {
            override fun onMessageClick(clickedMessage: Message, messageItemView: View) {
//                val actionWithArgument = MessagesFragmentDirections.actionMessagesFragmentToChatFragment(clickedMessage)
                findNavController().navigate(R.id.action_messagesFragment_to_chatFragment)
            }
        }
        sentMessagesRecyclerViewAdapter.messageClickListener = messageClickListener
        receivedMessagesRecyclerViewAdapter.messageClickListener = messageClickListener
        latestMessagesRecyclerViewAdapter.messageClickListener = messageClickListener
    }

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
        configureRecyclerViews()
    }

    private fun configureButtons() {
        my_profile_button.setOnClickListener {
            loadProfileFragment()
        }
    }

    private fun loadProfileFragment() {
        findNavController().navigate(R.id.action_messagesFragment_to_profileFragment)
    }

    private fun configureRecyclerViews() {
        configureRecyclerView(sent_recyclerview, sentMessagesRecyclerViewAdapter)
        configureRecyclerView(received_recyclerview, receivedMessagesRecyclerViewAdapter)
        configureRecyclerView(latest_recyclerview, latestMessagesRecyclerViewAdapter)
    }

    private fun configureRecyclerView(recyclerView: RecyclerView, messagesAdapter: MessagesPreviewRecyclerViewAdapter) {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = messagesAdapter
        }
    }

}
