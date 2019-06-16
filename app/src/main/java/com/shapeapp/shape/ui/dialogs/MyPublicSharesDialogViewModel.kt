package com.shapeapp.shape.ui.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.entities.Card
import com.shapeapp.shape.repositories.CardsRepository

/**
 * [ViewModel] for [MyPublicSharesDialog]
 */
class MyPublicSharesDialogViewModel(private val cardRepository: CardsRepository) : ViewModel() {

    //  TODO: delete ([MyPublicSharesDialog] was replaced with [MyPublicSharesBSD])

    var myPublicSharesCards: LiveData<List<Card>>
        private set

    init {
        myPublicSharesCards = cardRepository.publicSharesCards
    }


}