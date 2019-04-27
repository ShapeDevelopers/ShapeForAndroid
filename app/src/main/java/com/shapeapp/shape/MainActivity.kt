package com.shapeapp.shape


import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.shapeapp.shape.fragmentinterfaces.FragmentLoadingDemandListener
import com.shapeapp.shape.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Provides in-app navigation with Navigation Component
 */
class MainActivity : AppCompatActivity(), FragmentLoadingDemandListener,
    ProfileFragment.OnFragmentInteractionListener {

    //  TODO: change implementation of listeners
    //  TODO: maybe one extracted listener for all Fragments (?)
    //  TODO: move that listener to separate file

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureBottomNavigation()
    }

    private fun configureBottomNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        bottom_navigation_view.setupWithNavController(navController)
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestLoadFragment(fragmentToLoad: Fragment) {
        //  TODO: delete
    }
}


