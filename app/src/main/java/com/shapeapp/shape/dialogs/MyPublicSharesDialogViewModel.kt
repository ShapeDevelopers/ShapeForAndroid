package com.shapeapp.shape.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.Card
import com.shapeapp.shape.dialogs.MyPublicSharesDialog
import com.shapeapp.shape.repositories.Repository

/**
 * [ViewModel] for [MyPublicSharesDialog]
 */
class MyPublicSharesDialogViewModel(private val cardRepository: Repository) : ViewModel() {

    var myPublicSharesCards: LiveData<List<Card>>
        private set

    init {
        myPublicSharesCards = cardRepository.publicSharesCards
    }


}