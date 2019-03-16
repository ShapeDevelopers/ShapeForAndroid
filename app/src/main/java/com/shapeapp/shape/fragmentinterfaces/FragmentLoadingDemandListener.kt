package com.shapeapp.shape.fragmentinterfaces

import androidx.fragment.app.Fragment

interface FragmentLoadingDemandListener {

    fun requestLoadFragment(fragmentToLoad: Fragment)

}