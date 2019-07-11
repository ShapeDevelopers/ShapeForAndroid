package com.shapeapp.shape.ui.receivedimage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.shapeapp.shape.R
import com.shapeapp.shape.data.database.entities.Card
import com.shapeapp.shape.ui.gesturesdetection.OnFourWaysSwipeListener
import kotlinx.android.synthetic.main.fragment_received_image.*


/**
 * Shows data based on given [Card]
 */
class ReceivedImageFragment : Fragment() {

    private lateinit var viewModel: ReceivedImageViewModel

    private val args: ReceivedImageFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val card = args.card
        val viewModelFactory = ReceivedImageViewModelFactory(card)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReceivedImageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_received_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enableSwipeToClose()
        connectUiToViewModel()
        setVoteClickListeners()
    }

    private fun enableSwipeToClose() {
        root_layout.setOnTouchListener(object : OnFourWaysSwipeListener(context!!) {
            override fun onSwipeBottom() {
                findNavController().navigate(R.id.action_receivedImageFragment_to_publicFragment)
            }
        })
    }

    private fun connectUiToViewModel() {
        viewModel.let {
            it.extraText.observe(this, Observer { extraText -> extra_text_textview.text = extraText })
            it.backgroundImageUri.observe(
                this,
                Observer { backgroundImageUri ->
                    Glide.with(this@ReceivedImageFragment).load(backgroundImageUri).into(background_imageview)
                })
            it.senderNickname.observe(
                this,
                Observer { senderNickname -> sender_nickname_textview.text = senderNickname })
            it.imageRemainingTime.observe(
                this,
                Observer { remainingTime -> sender_image_remaining_time_textview.text = remainingTime })
            it.forCounterIndication.observe(
                this,
                Observer { forCounterIndication -> for_counter_textview.text = forCounterIndication })
            it.againstCounterIndication.observe(
                this,
                Observer { againstCounterIndication ->
                    against_counter_textview.text = againstCounterIndication
                })
        }
    }

    private fun setVoteClickListeners() {
        vote_for_fab.setOnClickListener { viewModel.forCounterClicked() }
        vote_against_fab.setOnClickListener { viewModel.againstCounterClicked() }
    }
}
