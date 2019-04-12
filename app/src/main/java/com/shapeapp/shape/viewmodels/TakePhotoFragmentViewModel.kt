package com.shapeapp.shape.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.fragments.TakePhotoFragment

/**
 * [ViewModel] for [TakePhotoFragment]
 */
class TakePhotoFragmentViewModel : ViewModel() {

    private val _photoUri = MutableLiveData<Uri>()
    val photoUri: LiveData<Uri>
        get() = _photoUri

    private var possiblePhotoUri: Uri? = null

    init {
        _photoUri.value = getEmptyContentUri()
    }

    /**
     * See: https://developer.android.com/reference/android/widget/ImageView#setImageURI(android.net.Uri)
     */
    private fun getEmptyContentUri(): Uri? = null

    fun startedWaitingForPhoto(possiblePhotoUri: Uri) {
        this.possiblePhotoUri = possiblePhotoUri
    }

    fun finishedWaitingForPhoto() {
        _photoUri.value = possiblePhotoUri
    }
}