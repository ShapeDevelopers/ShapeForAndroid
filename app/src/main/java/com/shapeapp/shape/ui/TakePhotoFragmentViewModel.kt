package com.shapeapp.shape.ui

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * [ViewModel] for [TakePhotoFragment]
 */
class TakePhotoFragmentViewModel : ViewModel() {

    private val _photoUri = MutableLiveData<Uri>()
    val photoUri: LiveData<Uri>
        get() = _photoUri

    /**
     * Holds [Uri] for possible photo file when View is waiting for result from camera
     */
    private var possiblePhotoUri: Uri? = null

    private val _isCameraIconVisible = MutableLiveData<Boolean>()
    val isCameraIconVisible: LiveData<Boolean>
        get() = _isCameraIconVisible

    private val _isTakenPhotoVisible = MutableLiveData<Boolean>()
    val isTakenPhotoVisible: LiveData<Boolean>
        get() = _isTakenPhotoVisible

    init {
        _photoUri.value = getEmptyContentUri()
        _isCameraIconVisible.value = true
        _isTakenPhotoVisible.value = false
    }

    /**
     * Gets [Uri] corresponding to empty content for single view
     * See: https://developer.android.com/reference/android/widget/ImageView#setImageURI(android.net.Uri)
     */
    private fun getEmptyContentUri(): Uri? = null

    /**
     * Invoked when View starts waiting for result from camera
     */
    fun startedWaitingForPhoto(possiblePhotoUri: Uri) {
        this.possiblePhotoUri = possiblePhotoUri
    }

    /**
     * Invoked when View gets result from camera
     */
    fun finishedWaitingForPhoto() {
        _photoUri.value = possiblePhotoUri
        _isCameraIconVisible.value = false
        _isTakenPhotoVisible.value = true
    }
}