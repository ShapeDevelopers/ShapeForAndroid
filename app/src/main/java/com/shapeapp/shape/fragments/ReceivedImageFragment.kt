package com.shapeapp.shape.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shapeapp.shape.R
import com.shapeapp.shape.data.Card
import com.shapeapp.shape.gesturesdetection.OnFourWaysSwipeListener
import com.shapeapp.shape.viewmodels.ReceivedImageFragmentViewModel
import com.shapeapp.shape.viewmodels.ReceivedImageFragmentViewModelFactory
import kotlinx.android.synthetic.main.fragment_received_image.*

private const val ARG_CARD_PARCELABLE = "ARG_CARD_PARCELABLE"

/**
 * Shows data based on given [Card]
 * Use the [ReceivedImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReceivedImageFragment : Fragment() {

    private lateinit var viewModel: ReceivedImageFragmentViewModel


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param card given [Card].
         * @return A new instance of fragment ReceivedImageFragment.
         */
        @JvmStatic
        fun newInstance(card: Card) =
            ReceivedImageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("ARG_CARD_PARCELABLE", card)
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val card: Card = it.getParcelable(ARG_CARD_PARCELABLE) ?: Card()
            val viewModelFactory = ReceivedImageFragmentViewModelFactory(card)
            viewModel =
                ViewModelProviders.of(this, viewModelFactory).get(ReceivedImageFragmentViewModel::class.java)
        }
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
                activity?.run { supportFragmentManager.popBackStack() }
            }
        })
    }

    private fun connectUiToViewModel() {
        viewModel.let {
            it.extraText.observe(this, Observer { extraText -> extra_text_textview.text = extraText })
            it.backgroundImageUri.observe(
                this,
                Observer { backgroundImageUri -> background_imageview.setImageURI(backgroundImageUri) })
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
