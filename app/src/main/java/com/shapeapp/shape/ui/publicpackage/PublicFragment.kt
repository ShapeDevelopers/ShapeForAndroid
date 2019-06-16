package com.shapeapp.shape.ui.publicpackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shapeapp.shape.R
import com.shapeapp.shape.data.database.entities.Card
import com.shapeapp.shape.ui.dialogs.MyPublicSharesBSD
import com.shapeapp.shape.internal.mockupsmakers.UsersMockups
import com.shapeapp.shape.ui.recyclerviews.adapters.SmallCardRecyclerViewAdapter
import com.shapeapp.shape.ui.recyclerviews.interfaces.RecyclerViewCardClickListener
import com.shapeapp.shape.data.repositories.CardsRepository
import kotlinx.android.synthetic.main.fragment_public.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_USER_AVATAR_URI = "ARG_USER_AVATAR_URI"

/**
 * Shows: new, official, and latest Cards lists.
 *
 * A simple [Fragment] subclass.
 * Use the [PublicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PublicFragment : Fragment() {
    //  TODO: replace my profile button with user avatar
    //  TODO: use Safe Args in order to retrieve parameters

    private lateinit var viewModel: PublicViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userAvatarUri = it.getString(ARG_USER_AVATAR_URI)
        }
        obtainViewModel()
        feedRecyclerViewAdapters()
        setClickListenerForRecyclerViewsAdapters()
    }

    private fun obtainViewModel() {
        val cardRepository = CardsRepository
        val viewModelFactory = PublicViewModelFactory(cardRepository)
        viewModel =
            activity?.run { ViewModelProviders.of(this, viewModelFactory).get(PublicViewModel::class.java) }
                ?: throw Exception("Invalid Activity")
    }

    private fun feedRecyclerViewAdapters() {
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
                val action =
                    PublicFragmentDirections.actionPublicFragmentToReceivedImageFragment2(
                        clickedCard
                    )
                findNavController().navigate(action)
            }
        }
        newCardsRecyclerViewAdapter.cardClickListener = cardClickListener
        officialCardsRecyclerViewAdapter.cardClickListener = cardClickListener
        latestCardsRecyclerViewAdapter.cardClickListener = cardClickListener
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
        my_public_shares_button.setOnClickListener { showMyPublicSharesBSD() }
    }

    private fun loadProfileFragment() {
        //  TODO: provide real User or UsersRepository
        val user = UsersMockups.randomUser
        val actionWithArgument =
            PublicFragmentDirections.actionPublicFragmentToProfileFragment(user)
        findNavController().navigate(actionWithArgument)
    }

    private fun showMyPublicSharesBSD() {
        //  TODO: wait for support for BottomSheetDialogFragment in Navigation Component, then reimplement
        fragmentManager?.let { MyPublicSharesBSD().show(it, "BSD") }
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
}
