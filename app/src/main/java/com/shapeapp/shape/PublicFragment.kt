package com.shapeapp.shape

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.fragment_public.*

//  TODO: check and change whole file
//  TODO: use MVVM


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PublicFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PublicFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PublicFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_public, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  TODO: check this
        // TODO: add elements dynamically
//        createAndAddCardView()
    }

//    private fun createAndAddCardView() {
//
//        //  TODO: change name and content
//
//        val card = createCard()
//        val image = createImage()
//        val linearLayout = createLinearLayout()
//
//        linearLayout.addView(image)
//        card.addView(linearLayout)
//
//        official_card_list_layout.removeAllViewsInLayout()
//        official_card_list_layout.addView(card)
//    }
//
//    private fun createCard(): MaterialCardView {
//
//        //  TODO: change name and content
//
//        val width = resources.getDimensionPixelSize(R.dimen.small_card_width)
//        val height = resources.getDimensionPixelSize(R.dimen.small_card_height)
//
//        val layoutParams = LinearLayout.LayoutParams(width, height)
//
//        val margin = resources.getDimensionPixelSize(R.dimen.small_card_margin)
//        layoutParams.setMargins(margin)
//
//        val card = MaterialCardView(context)
//
//        card.layoutParams = layoutParams
//        card.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorAccent))
//        val radius = resources.getDimensionPixelSize(R.dimen.small_card_corner_radius).toFloat()
//        card.radius = radius
//
//        official_card_list_layout.addView(card)
//
//        return card
//
//    }
//
//    private fun createImage(): ImageView {
//
//        //  TODO: change name and content
//
//        val image = ImageView(context)
//        val layoutParams =
//            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
//        image.layoutParams = layoutParams
//        image.scaleType = ImageView.ScaleType.CENTER_CROP
//        image.setImageResource(R.drawable.near_me_icon)
//
//        return image
//
//    }

    private fun createLinearLayout(): LinearLayout {
        val linearLayout = LinearLayout(context)
        val layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        linearLayout.layoutParams = layoutParams
        linearLayout.orientation = LinearLayout.HORIZONTAL

        return linearLayout
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
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PublicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PublicFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
