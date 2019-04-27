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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shapeapp.shape.R
import com.shapeapp.shape.data.Card
import com.shapeapp.shape.dialogs.MyPublicSharesDialog
import com.shapeapp.shape.fragmentinterfaces.FragmentLoadingDemandListener
import com.shapeapp.shape.recyclerviewadapters.SmallCardRecyclerViewAdapter
import com.shapeapp.shape.recyclerviewinterfaces.RecyclerViewCardClickListener
import com.shapeapp.shape.repositories.Repository
import com.shapeapp.shape.viewmodels.PublicFragmentViewModel
import com.shapeapp.shape.viewmodels.PublicFragmentViewModelFactory
import kotlinx.android.synthetic.main.fragment_public.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_USER_AVATAR_URI = "ARG_USER_AVATAR_URI"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentLoadingDemandListener] interface
 * to handle interaction events.
 * Use the [PublicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PublicFragment : Fragment() {
    //  TODO: replace my profile button with user avatar

    private lateinit var viewModel: PublicFragmentViewModel

    private var fragmentLoadingDemandListener: FragmentLoadingDemandListener? = null
    private var userAvatarUri: String? = null
    private val officialCardsRecyclerViewAdapter = SmallCardRecyclerViewAdapter(emptyArray())
    private val newCardsRecyclerViewAdapter = SmallCardRecyclerViewAdapter(emptyArray())
    private val latestCardsRecyclerViewAdapter = SmallCardRecyclerViewAdapter(emptyArray())


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param userAvatarUri URI to user's avatar.
         * @return A new instance of fragment PublicFragment.
         */
        @JvmStatic
        fun newInstance(userAvatarUri: String) =
            PublicFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_AVATAR_URI, userAvatarUri)
                }
            }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentLoadingDemandListener) {
            fragmentLoadingDemandListener = context
        } else {
            throw RuntimeException("$context must implement FragmentLoadingDemandListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userAvatarUri = it.getString(ARG_USER_AVATAR_URI)
        }
        configureViewModel()
        connectRecyclerViewAdaptersToViewModel()
        setClickListenerForRecyclerViewsAdapters()
    }

    private fun configureViewModel() {
        val cardRepository = Repository
        val viewModelFactory = PublicFragmentViewModelFactory(cardRepository)
        viewModel =
            activity?.run { ViewModelProviders.of(this, viewModelFactory).get(PublicFragmentViewModel::class.java) }
                ?: throw Exception("Invalid Activity")
    }

    private fun connectRecyclerViewAdaptersToViewModel() {
        //  for "New" RecyclerView
        viewModel.newCards.observe(this, Observer { newCards ->
            newCardsRecyclerViewAdapter.changeDataAndNotify(newCards)
        })
        //  for "Official" RecyclerView
        viewModel.officialCards.observe(this, Observer { officialCards ->
            officialCardsRecyclerViewAdapter.changeDataAndNotify(officialCards)
        })
        // for "Latest" RecyclerView
        viewModel.latestCards.observe(this, Observer { latestCards ->
            latestCardsRecyclerViewAdapter.changeDataAndNotify(latestCards)
        })
    }

    /**
     * [SmallCardRecyclerViewAdapter] extension function
     *
     * Used for convenient way to change [SmallCardRecyclerViewAdapter]'s dataset and notify about it
     */
    private fun SmallCardRecyclerViewAdapter.changeDataAndNotify(cardsData: List<Card>) {
        myDataset = cardsData.toTypedArray()
        notifyDataSetChanged()
    }

    private fun setClickListenerForRecyclerViewsAdapters() {
        val cardClickListener = object : RecyclerViewCardClickListener {
            override fun onCardClick(clickedCard: Card, cardItemView: View) {
                fragmentLoadingDemandListener?.requestLoadFragment(
                    ReceivedImageFragment.newInstance(clickedCard)
                )
            }
        }
        newCardsRecyclerViewAdapter.recyclerViewCardClickListener = cardClickListener
        officialCardsRecyclerViewAdapter.recyclerViewCardClickListener = cardClickListener
        latestCardsRecyclerViewAdapter.recyclerViewCardClickListener = cardClickListener
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

        configureRecyclerViews()
        setButtonsOnClickListeners()
        showSnackbar()
    }

    private fun configureRecyclerViews() {
        configureRecyclerView(new_card_list_recyclerview, newCardsRecyclerViewAdapter)
        configureRecyclerView(official_card_list_recyclerview, officialCardsRecyclerViewAdapter)
        configureRecyclerView(latest_card_list_recyclerview, latestCardsRecyclerViewAdapter)
    }

    private fun configureRecyclerView(recyclerView: RecyclerView, cardsAdapter: SmallCardRecyclerViewAdapter) {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = cardsAdapter
        }
    }

    private fun setButtonsOnClickListeners() {
        my_profile_button.setOnClickListener { loadProfileFragment() }
        //  TODO: replace with [BottomSheetDialog] (?)
        my_public_shares_button.setOnClickListener { showMyPublicSharesDialog() }
    }

    private fun loadProfileFragment() {
        findNavController().navigate(R.id.action_publicFragment_to_profileFragment3)
    }

    private fun showMyPublicSharesDialog() {
        fragmentManager?.let { MyPublicSharesDialog().show(it, "dialog") }
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

    override fun onDetach() {
        super.onDetach()
        fragmentLoadingDemandListener = null
    }
}
