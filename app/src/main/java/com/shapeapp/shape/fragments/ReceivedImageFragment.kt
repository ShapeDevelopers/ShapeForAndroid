package com.shapeapp.shape.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shapeapp.shape.R
import com.shapeapp.shape.data.Card
import com.shapeapp.shape.gesturesdetection.OnFourWaysSwipeListener
import kotlinx.android.synthetic.main.fragment_received_image.*

//  TODO: check and change whole file
//  TODO: use MVVM

//  TODO: implement [Parcelable] interface in [Card] to pass it in [Bundle]

private const val ARG_CARD_TYPE = "ARG_CARD_TYPE"
private const val ARG_CARD_EXTRA_TEXT = "ARG_CARD_EXTRA_TEXT"
private const val ARG_CARD_IMAGE_URL = "ARG_CARD_IMAGE_URL"
private const val ARG_CARD_SENDER_NICKNAME = "ARG_CARD_SENDER_NICKNAME"
private const val ARG_CARD_REMAINING_TIME_IN_MIN = "ARG_CARD_REMAINING_TIME_IN_MIN"
private const val ARG_CARD_VOTES_FOR_COUNTER = "ARG_CARD_VOTES_FOR_COUNTER"
private const val ARG_CARD_VOTES_AGAINST_COUNTER = "ARG_CARD_VOTES_AGAINST_COUNTER"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ReceivedImageFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ReceivedImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ReceivedImageFragment : Fragment() {
    private var cardType: String? = null
    private var cardExtraText: String? = null
    private var cardImageUrl: String? = null
    private var cardSender: String? = null
    private var cardRemainingTimeInMin: Int? = null
    private var cardVotesForCounter: Int? = null
    private var cardVotesAgainstCounter: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cardType = it.getString(ARG_CARD_TYPE)
            cardExtraText = it.getString(ARG_CARD_EXTRA_TEXT)
            cardImageUrl = it.getString(ARG_CARD_IMAGE_URL)
            cardSender = it.getString(ARG_CARD_SENDER_NICKNAME)
            cardRemainingTimeInMin = it.getInt(ARG_CARD_REMAINING_TIME_IN_MIN)
            cardVotesForCounter = it.getInt(ARG_CARD_VOTES_FOR_COUNTER)
            cardVotesAgainstCounter = it.getInt(ARG_CARD_VOTES_AGAINST_COUNTER)
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

        //  TODO: delete and replace with close action after swipe
        root_layout.setOnTouchListener(object : OnFourWaysSwipeListener(context!!) {
            override fun onSwipeBottom() {
                activity?.run { supportFragmentManager.popBackStack() }
            }
        })

        //  TODO: delete (?)
        extra_text_textview.text = cardExtraText
        background_imageview.setImageURI(Uri.parse(cardImageUrl))
        sender_nickname_textview.text = cardSender
        sender_image_remaining_time_textview.text = cardRemainingTimeInMin.toString()
        for_counter_textview.text = cardVotesForCounter.toString()
        against_counter_textview.text = cardVotesAgainstCounter.toString()
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param cardType type of the card.
         * @param clickedCard simply [Card].
         * @return A new instance of fragment ReceivedImageFragment.
         */
        @JvmStatic
        fun newInstance(cardType: String, clickedCard: Card) =
            ReceivedImageFragment().apply {
                arguments = Bundle().apply {
                    //  TODO: delete (?)
                    putString(ARG_CARD_TYPE, cardType)

                    //  TODO: clean-up (use Bundle and Parcelable interface)
                    putString(ARG_CARD_EXTRA_TEXT, clickedCard.extraText)
                    putString(ARG_CARD_IMAGE_URL, clickedCard.imageUrl)
                    putString(ARG_CARD_SENDER_NICKNAME, clickedCard.senderNickname)
                    putInt(ARG_CARD_REMAINING_TIME_IN_MIN, clickedCard.remainingTimeInMin)
                    putInt(ARG_CARD_VOTES_FOR_COUNTER, clickedCard.votesForCounter)
                    putInt(ARG_CARD_VOTES_AGAINST_COUNTER, clickedCard.votesAgainstCounter)
                }
            }
    }
}
