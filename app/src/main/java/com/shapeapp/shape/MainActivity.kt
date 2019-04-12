package com.shapeapp.shape


import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.shapeapp.shape.fragmentinterfaces.FragmentLoadingDemandListener
import com.shapeapp.shape.fragments.MessagesFragment
import com.shapeapp.shape.fragments.ProfileFragment
import com.shapeapp.shape.fragments.PublicFragment
import com.shapeapp.shape.fragments.TakePhotoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FragmentLoadingDemandListener,
    ProfileFragment.OnFragmentInteractionListener,
    TakePhotoFragment.OnFragmentInteractionListener {

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
        //  TODO: add other Fragments to load
        //  TODO: change arguments for Fragments
        bottom_navigation_view.setOnNavigationItemSelectedListener { currentItem: MenuItem ->
            when (currentItem.itemId) {
                R.id.action_inbox -> loadFragment(MessagesFragment.newInstance("FIRST", "SECOND"))
                // TODO: change parameters
                R.id.action_camera -> loadFragment(TakePhotoFragment.newInstance())
                //  TODO: provide real URI
                R.id.action_public -> loadFragment(PublicFragment.newInstance("FAKE_USER_AVATAR_URI"))
                // TODO: accomplish loading near me
                R.id.action_near_me -> {
                }
            }
            true
        }
    }

    private fun loadFragment(fragmentToLoad: Fragment) {
        when (isAnyFragmentLoaded()) {
            true -> {
                supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.fragment_container, fragmentToLoad)
                    .addToBackStack(null)
                    .commit()
            }
            false -> {
                supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .add(R.id.fragment_container, fragmentToLoad)
                    .addToBackStack(null)
                    .commit()
            }

        }
    }

    private fun isAnyFragmentLoaded(): Boolean {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        return when (fragment) {
            null -> false
            else -> true
        }
    }

    /**
     * Behaves the same as tapping on the Public action
     */
    private fun tapOnPublicAction() {
        bottom_navigation_view.selectedItemId = R.id.action_public
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestLoadFragment(fragmentToLoad: Fragment) {
        loadFragment(fragmentToLoad)
    }

    /**
     * Prevents showing blank screen without [Fragment] loaded (after pressing back key).
     */
    override fun onBackPressed() {
        val entriesOnBackStackNumber = supportFragmentManager.backStackEntryCount
        when (entriesOnBackStackNumber) {
            1 -> finish()
            else -> supportFragmentManager.popBackStack()
        }
    }
}


