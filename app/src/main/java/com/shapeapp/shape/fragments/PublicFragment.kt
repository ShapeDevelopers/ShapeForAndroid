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
import com.shapeapp.shape.R
import com.shapeapp.shape.recyclerviewadapters.SmallCardRecyclerViewAdapter
import com.shapeapp.shape.viewmodels.PublicFragmentViewModel
import kotlinx.android.synthetic.main.fragment_public.*

//  TODO: check and change whole file
//  TODO: use MVVM

//  TODO: complete implementing [RecyclerView] for Official
//  TODO: replace old mechanism with [RecyclerView] for New
//  TODO: replace old mechanism with [RecyclerView] for Latest

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

    private lateinit var officialCardsRecyclerViewAdapter: SmallCardRecyclerViewAdapter
    private lateinit var newCardsRecyclerViewAdapter: SmallCardRecyclerViewAdapter
    private lateinit var latestCardsRecyclerViewAdapter: SmallCardRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //  TODO: clean
        configureViewModel()
    }

    private fun configureViewModel() {
        //  TODO: clean
        //  ViewModel
        val viewModel = ViewModelProviders.of(this).get(PublicFragmentViewModel::class.java)
        //  for "Official" RecyclerView
        viewModel.getOfficialCardsData()
            .observe(this, Observer<List<String>> { users -> changeOfficialCardsAdapterData(users) })
        //  for "New" RecyclerView
        viewModel.getNewCardsData()
            .observe(this, Observer<List<String>> { newCards -> changeNewCardsAdapterData(newCards) })
        // for "latest" RecyclerView
        viewModel.getLatestCardsData()
            .observe(this, Observer<List<String>> { latestCards -> changeLatestCardsAdapterData(latestCards) })
    }

    private fun changeOfficialCardsAdapterData(data: List<String>) {
        //  TODO: clean
        //  TODO: move to [SmallCardRecyclerViewAdapter] (?)
        officialCardsRecyclerViewAdapter.myDataset = data.toTypedArray()
        officialCardsRecyclerViewAdapter.notifyDataSetChanged()
    }

    private fun changeNewCardsAdapterData(data: List<String>) {
        //  TODO: clean
        //  TODO: move to [SmallCardRecyclerViewAdapter] (?)
        //  TODO: merge to one function
        newCardsRecyclerViewAdapter.myDataset = data.toTypedArray()
        newCardsRecyclerViewAdapter.notifyDataSetChanged()
    }

    private fun changeLatestCardsAdapterData(data: List<String>) {
        //  TODO: clean
        //  TODO: move to [SmallCardRecyclerViewAdapter] (?)
        //  TODO: merge to one function
        latestCardsRecyclerViewAdapter.myDataset = data.toTypedArray()
        latestCardsRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureOfficialCardsRecyclerView()
        configureNewCardsRecyclerView()
        configureLatestCardsRecyclerView()
    }

    private fun configureOfficialCardsRecyclerView() {
        val viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        officialCardsRecyclerViewAdapter = SmallCardRecyclerViewAdapter(emptyArray())

        official_card_list_recyclerview.apply {
            layoutManager = viewManager
            adapter = officialCardsRecyclerViewAdapter
        }
    }

    private fun configureNewCardsRecyclerView() {
        //  TODO: merge to one function
        val viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        newCardsRecyclerViewAdapter = SmallCardRecyclerViewAdapter(emptyArray())

        new_card_list_recyclerview.apply {
            layoutManager = viewManager
            adapter = newCardsRecyclerViewAdapter
        }
    }

    private fun configureLatestCardsRecyclerView() {
        //  TODO: merge to one function
        val viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        latestCardsRecyclerViewAdapter = SmallCardRecyclerViewAdapter(emptyArray())

        latest_card_list_recyclerview.apply {
            layoutManager = viewManager
            adapter = latestCardsRecyclerViewAdapter
        }
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
