package com.shapeapp.shape


import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PublicFragment.OnFragmentInteractionListener {

    //  TODO: change implementation of listener
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
        //  TODO: change arguments for PublicFragment
        bottom_navigation_view.setOnNavigationItemSelectedListener { currentItem: MenuItem ->
            when (currentItem.itemId) {
//                R.id.action_inbox -> loadFragment()
//                R.id.action_camera -> loadFragment()
                R.id.action_public -> loadFragment(PublicFragment.newInstance("FIRST", "SECOND"))
//                R.id.action_near_me -> loadFragment()
            }
            true
        }
    }

    private fun loadFragment(fragmentToLoad: Fragment) {
        when (isAnyFragmentLoaded()) {
            true -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragmentToLoad)
                    .commit()
            }
            false -> {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragmentToLoad)
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
}


