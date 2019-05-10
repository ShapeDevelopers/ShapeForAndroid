package com.shapeapp.shape.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.Card
import com.shapeapp.shape.repositories.Repository

/**
 * [ViewModel] for [MyPublicSharesDialog]
 */
class MyPublicSharesDialogViewModel(private val cardRepository: Repository) : ViewModel() {

    //  TODO: delete ([MyPublicSharesDialog] was replaced with [MyPublicSharesBSD])

    var myPublicSharesCards: LiveData<List<Card>>
        private set

    init {
        myPublicSharesCards = cardRepository.publicSharesCards
    }


}