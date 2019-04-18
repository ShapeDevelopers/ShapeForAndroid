package com.shapeapp.shape.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shapeapp.shape.R
import com.shapeapp.shape.backendcalls.RetrofitBackendClient
import com.shapeapp.shape.backendcalls.User
import kotlinx.android.synthetic.main.fragment_initial_backend_connection.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 *
 * Use the [InitialBackendConnectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class InitialBackendConnectionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  TODO: delete
        makeNetworkCall()
    }

    private fun makeNetworkCall() {
        //  TODO: delete

        val backendApi = RetrofitBackendClient.backendApi

        val networkCall = backendApi.getUser()

        networkCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                when (response.isSuccessful) {
                    true -> {
                        val user = response.body()
                        val name = user?.name ?: "No name provided"
                        val surname = user?.surname ?: "No surname provided"
                        val email = user?.email ?: "No email provided"
                        val birthDate = user?.birthDate ?: "No birth date provided"

                        val receivedData = "name: $name \nsurname: $surname \nemail: $email \nbirthDate: $birthDate"
                        response_textview.text = receivedData
                    }
                    false -> {
                        val responseCode = response.code()
                        val message = "Code: $responseCode"
                        response_textview.text = message
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                val failureMessage = "Failure: ${t.message}"
                response_textview.text = failureMessage
            }
        })
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
