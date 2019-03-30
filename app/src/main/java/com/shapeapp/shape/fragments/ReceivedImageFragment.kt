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

private const val ARG_CARD_PARCELABLE = "ARG_CARD_PARCELABLE"

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

    private var listener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val card: Card = it.getParcelable(ARG_CARD_PARCELABLE) ?: Card()
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

        root_layout.setOnTouchListener(object : OnFourWaysSwipeListener(context!!) {
            override fun onSwipeBottom() {
                activity?.run { supportFragmentManager.popBackStack() }
            }
        })

        //  TODO: delete (?)
//        extra_text_textview.text = cardExtraText
//        background_imageview.setImageURI(Uri.parse(cardImageUrl))
//        sender_nickname_textview.text = cardSender
//        sender_image_remaining_time_textview.text = cardRemainingTimeInMin.toString()
//        for_counter_textview.text = cardVotesForCounter.toString()
//        against_counter_textview.text = cardVotesAgainstCounter.toString()
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
        fun newInstance(clickedCard: Card) =
            ReceivedImageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("ARG_CARD_PARCELABLE", clickedCard)
                }
            }
    }
}
