package com.shapeapp.shape.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shapeapp.shape.R
import com.shapeapp.shape.data.Card
import com.shapeapp.shape.dialogs.PublicSharesDialog
import com.shapeapp.shape.fragmentinterfaces.FragmentLoadingDemandListener
import com.shapeapp.shape.recyclerviewadapters.SmallCardRecyclerViewAdapter
import com.shapeapp.shape.recyclerviewinterfaces.RecyclerViewCardClickListener
import com.shapeapp.shape.repositories.Repository
import com.shapeapp.shape.viewmodels.PublicFragmentViewModel
import com.shapeapp.shape.viewmodels.PublicFragmentViewModelFactory
import kotlinx.android.synthetic.main.fragment_public.*

//  TODO: check and change whole file
//  TODO: use MVVM

//  TODO: refactor, class is too large and complex!

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentLoadingDemandListener] interface
 * to handle interaction events.
 * Use the [PublicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PublicFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var fragmentLoadingDemandListener: FragmentLoadingDemandListener? = null

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
        val cardRepository = Repository
        val viewModelFactory = PublicFragmentViewModelFactory(cardRepository)
        viewModel =
            activity?.run { ViewModelProviders.of(this, viewModelFactory).get(PublicFragmentViewModel::class.java) }
                ?: throw Exception("Invalid Activity")
        //  for "New" RecyclerView
        viewModel.newCardsData.observe(this, Observer<List<Card>> { newCards ->
            changeCardsAdapterData(
                newCards,
                newCardsRecyclerViewAdapter
            )
        })
        //  for "Official" RecyclerView
        viewModel.officialCardsData.observe(this, Observer<List<Card>> { officialCards ->
            changeCardsAdapterData(
                officialCards,
                officialCardsRecyclerViewAdapter
            )
        })
        // for "Latest" RecyclerView
        viewModel.latestCardsData.observe(this, Observer<List<Card>> { latestCards ->
            changeCardsAdapterData(
                latestCards,
                latestCardsRecyclerViewAdapter
            )
        })
    }

    private fun changeCardsAdapterData(cardsData: List<Card>, cardAdapter: SmallCardRecyclerViewAdapter) {
        cardAdapter.myDataset = cardsData.toTypedArray()
        cardAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListenerForAllRecyclerViewsAdapters()

        configureCardsRecyclerView(new_card_list_recyclerview, newCardsRecyclerViewAdapter)
        configureCardsRecyclerView(official_card_list_recyclerview, officialCardsRecyclerViewAdapter)
        configureCardsRecyclerView(latest_card_list_recyclerview, latestCardsRecyclerViewAdapter)

        setOnClickListeners()

        showSnackbar()
    }

    private fun setClickListenerForAllRecyclerViewsAdapters() {
        //  TODO: change overall behaviour to pass card data to [ReceivedImageFragment] to better one (?)

        val cardClickListener = object : RecyclerViewCardClickListener {
            override fun onCardClick(clickedCard: Card, cardItemView: View) {
                fragmentLoadingDemandListener?.requestLoadFragment(
                    ReceivedImageFragment.newInstance("SOME_CARD", clickedCard)
                )
            }
        }

        newCardsRecyclerViewAdapter.recyclerViewCardClickListener = cardClickListener
        officialCardsRecyclerViewAdapter.recyclerViewCardClickListener = cardClickListener
        latestCardsRecyclerViewAdapter.recyclerViewCardClickListener = cardClickListener
    }


    private fun configureCardsRecyclerView(recyclerView: RecyclerView, cardsAdapter: SmallCardRecyclerViewAdapter) {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = cardsAdapter
        }
    }

    private fun setOnClickListeners() {
        my_profile_button.setOnClickListener { loadProfileFragment() }
        //  TODO: change showMyPublicSharesDialog invocation (?)
        my_public_shares_button.setOnClickListener { showMyPublicSharesDialog() }
    }

    private fun showMyPublicSharesDialog() {
        //  TODO: apply some parameters (?)
        fragmentManager?.let { PublicSharesDialog().show(it, "dialog") }
    }

    private fun loadProfileFragment() {
        //  TODO: change newInstance(...) parameters
        val profileFragment = ProfileFragment.newInstance("FIRST", "SECOND")
        fragmentLoadingDemandListener?.requestLoadFragment(profileFragment)
    }

    private fun showSnackbar() {
        //  TODO: delete this function
        val snackbar = Snackbar.make(root_coordinatorlayout, "Hello user!", Snackbar.LENGTH_SHORT)
        val greyColor = ContextCompat.getColor(context!!, R.color.colorHintGrey)
        snackbar.changeTextColor(greyColor)
        snackbar.show()
    }

    /**
     * [Snackbar] extension function
     *
     * Used for convenient way to change [Snackbar]'s [TextView] text color
     */
    private fun Snackbar.changeTextColor(color: Int) {
        val textView = view.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(color)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_public, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentLoadingDemandListener) {
            fragmentLoadingDemandListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement FragmentLoadingDemandListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentLoadingDemandListener = null
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
