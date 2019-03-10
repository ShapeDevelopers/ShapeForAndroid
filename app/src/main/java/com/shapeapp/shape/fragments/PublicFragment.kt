package com.shapeapp.shape.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shapeapp.shape.R
import com.shapeapp.shape.dialogs.PublicSharesDialog
import com.shapeapp.shape.recyclerviewadapters.SmallCardRecyclerViewAdapter
import com.shapeapp.shape.viewmodels.PublicFragmentViewModel
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

    private val officialCardsRecyclerViewAdapter = SmallCardRecyclerViewAdapter(emptyArray())
    private val newCardsRecyclerViewAdapter = SmallCardRecyclerViewAdapter(emptyArray())
    private val latestCardsRecyclerViewAdapter = SmallCardRecyclerViewAdapter(emptyArray())

    private lateinit var viewModel: PublicFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        configureViewModel()
    }

    private fun configureViewModel() {
        //  ViewModel remains in memory, in the case of a fragment, to the moment when Fragment is detached
        //  TODO: maybe it should use ViewModelProviders.of(context) ?
        //  TODO: do not use "!!"
        viewModel = ViewModelProviders.of(activity!!).get(PublicFragmentViewModel::class.java)
        //  for "New" RecyclerView
        viewModel.newCardsData.observe(this, Observer<List<String>> { newCards ->
            changeCardsAdapterData(
                newCards,
                newCardsRecyclerViewAdapter
            )
        })
        //  for "Official" RecyclerView
        viewModel.officialCardsData.observe(this, Observer<List<String>> { officialCards ->
            changeCardsAdapterData(
                officialCards,
                officialCardsRecyclerViewAdapter
            )
        })
        // for "Latest" RecyclerView
        viewModel.latestCardsData.observe(this, Observer<List<String>> { latestCards ->
            changeCardsAdapterData(
                latestCards,
                latestCardsRecyclerViewAdapter
            )
        })
    }

    private fun changeCardsAdapterData(cardsData: List<String>, cardAdapter: SmallCardRecyclerViewAdapter) {
        cardAdapter.myDataset = cardsData.toTypedArray()
        cardAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureCardsRecyclerView(new_card_list_recyclerview, newCardsRecyclerViewAdapter)
        configureCardsRecyclerView(official_card_list_recyclerview, officialCardsRecyclerViewAdapter)
        configureCardsRecyclerView(latest_card_list_recyclerview, latestCardsRecyclerViewAdapter)

        //  TODO: delete
        configureSimulatedSelection()

        //  TODO: delete
        showTestDialog()
    }

    private fun showTestDialog() {
        //  TODO: delete this function
        PublicSharesDialog().show(fragmentManager!!, "dialog")
    }

    private fun configureCardsRecyclerView(recyclerView: RecyclerView, cardsAdapter: SmallCardRecyclerViewAdapter) {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = cardsAdapter
        }
    }

    private fun configureSimulatedSelection() {
        //  TODO: delete
        //  simulate selection
        my_public_shares_fab.setOnClickListener { viewModel.selectCardText("Right button has been clicked") }
        my_profile_fab.setOnClickListener { viewModel.selectCardText("Left button has been clicked") }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_public, container, false)
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
