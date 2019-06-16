package com.shapeapp.shape.ui.initialbackendconnection

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shapeapp.shape.R
import com.shapeapp.shape.data.database.entities.Card
import com.shapeapp.shape.data.network.backendcalls.RetrofitBackendClient
import com.shapeapp.shape.data.network.status.NetworkInformer
import kotlinx.android.synthetic.main.fragment_initial_backend_connection.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * It is designed ONLY for testing the connection with network.
 *
 * Use the [InitialBackendConnectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InitialBackendConnectionFragment : Fragment() {

    //  TODO: delete (only for easy testing the connection)

    /**
     * Defines (network check) code to be sent by [Handler] to execution
     */
    private val checkNetworkRunnable: Runnable = object : Runnable {
        override fun run() {
            is_online_textview.text = NetworkInformer.isOnline().toString()
            handler.postDelayed(this, 1000)
        }
    }

    /**
     * Executes [Runnable] task on UI thread
     */
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        makeNetworkCall()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNetworkRunnable.run()
    }

    private fun makeNetworkCall() {
        val backendApi = RetrofitBackendClient.backendApi
        val networkCall = backendApi.getCards()

        networkCall.enqueue(object : Callback<List<Card>> {
            override fun onResponse(call: Call<List<Card>>, response: Response<List<Card>>) {
                when (response.isSuccessful) {
                    true -> {
                        val cards = response.body() ?: listOf()
                        val cardsAsText = cardsToTextList(cards)
                        response_textview?.text = cardsAsText
                    }
                    false -> {
                        val responseCode = response.code()
                        val message = "Code: $responseCode"
                        response_textview?.text = message
                    }
                }
            }

            override fun onFailure(call: Call<List<Card>>, t: Throwable) {
                val failureMessage = "Failure: ${t.message}"
                response_textview?.text = failureMessage
            }
        })
    }

    private fun cardsToTextList(cards: List<Card>): String {
        var cardsAsText = ""
        for (card in cards) {
            cardsAsText += "$card,\n"
        }
        return cardsAsText
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Remove pending code execution
        handler.removeCallbacks(checkNetworkRunnable)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_initial_backend_connection, container, false)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment
         *
         * @return A new instance of fragment InitialBackendConnectionFragment.
         */
        @JvmStatic
        fun newInstance() = InitialBackendConnectionFragment()
    }
}
