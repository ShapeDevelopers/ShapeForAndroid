package com.shapeapp.shape.fragmentinterfaces

import androidx.fragment.app.Fragment

//  TODO: delete this (use Navigation components and Safe args to handle Fragment loading)

/**
 * Activity must implement this interface in order to handle [Fragment] loading request from outside
 */
interface FragmentLoadingDemandListener {

    fun requestLoadFragment(fragmentToLoad: Fragment)

}