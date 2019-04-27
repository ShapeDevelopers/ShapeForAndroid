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
 * Manages viewing [Fragment]s on the screen and provides in-app navigation
 */
class MainActivity : AppCompatActivity(), FragmentLoadingDemandListener,
    ProfileFragment.OnFragmentInteractionListener {

    //  TODO: Use Navigation from Jetpack->Architecture Components
    //  TODO: change implementation of listeners
    //  TODO: maybe one extracted listener for all Fragments (?)
    //  TODO: move that listener to separate file

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureBottomNavigation()

        if (savedInstanceState == null) {
            tapOnPublicAction()
        }
    }

    private fun configureBottomNavigation() {
//        //  TODO: add other Fragments to load
//        //  TODO: change arguments for Fragments
//        bottom_navigation_view.setOnNavigationItemSelectedListener { currentItem: MenuItem ->
//            when (currentItem.itemId) {
//                R.id.action_inbox -> loadFragment(MessagesFragment.newInstance("FIRST", "SECOND"))
//                // TODO: change parameters
//                R.id.takePhotoFragment -> loadFragment(TakePhotoFragment.newInstance())
//                //  TODO: provide real URI
//                R.id.action_public -> loadFragment(PublicFragment.newInstance("FAKE_USER_AVATAR_URI"))
//                // TODO: Remove loading [InitialBackendConnectionFragment]
//                // TODO: accomplish loading near me with near me Fragment
//                R.id.action_near_me -> loadFragment(InitialBackendConnectionFragment.newInstance())
//            }
//            true
//        }
        val navController = findNavController(R.id.nav_host_fragment)
        bottom_navigation_view.setupWithNavController(navController)
    }

    private fun loadFragment(fragmentToLoad: Fragment) {
        //  TODO: delete (now Navigation Component handles this)
//        when (isAnyFragmentLoaded()) {
//            true -> {
//                supportFragmentManager
//                    .beginTransaction()
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .replace(R.id.fragment_container, fragmentToLoad)
//                    .addToBackStack(null)
//                    .commit()
//            }
//            false -> {
//                supportFragmentManager
//                    .beginTransaction()
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .add(R.id.fragment_container, fragmentToLoad)
//                    .addToBackStack(null)
//                    .commit()
//            }
//
//        }
    }

    /**
     * Behaves the same as tapping on the Public action
     */
    private fun tapOnPublicAction() {
        bottom_navigation_view.selectedItemId = R.id.publicFragment
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestLoadFragment(fragmentToLoad: Fragment) {
        loadFragment(fragmentToLoad)
    }
}


