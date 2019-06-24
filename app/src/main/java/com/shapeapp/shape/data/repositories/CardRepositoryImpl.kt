package com.shapeapp.shape.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shapeapp.shape.data.database.entities.Card
import com.shapeapp.shape.internal.mockupsmakers.CardMockups

/**
 * Implementation of [CardRepository]
 */
class CardRepositoryImpl : CardRepository {

    //  TODO: add model, remote data source and others
    //  see: https://developer.android.com/jetpack/docs/guide

    private val _officialCards = MutableLiveData<List<Card>>()

    private val _newCards = MutableLiveData<List<Card>>()

    private val _latestCards = MutableLiveData<List<Card>>()

    private val _publicSharesCards = MutableLiveData<List<Card>>()


    init {
        _officialCards.value = CardMockups.animalCards
        _newCards.value = CardMockups.nameCards
        _latestCards.value = CardMockups.cityCards
        _publicSharesCards.value = CardMockups.countryCards
    }


    override fun getOfficialCards(): LiveData<List<Card>> {
        return _officialCards
    }

    override fun getNewCards(): LiveData<List<Card>> {
        return _newCards
    }

    override fun getLatestCards(): LiveData<List<Card>> {
        return _latestCards
    }

    override fun getPublicSharesCards(): LiveData<List<Card>> {
        return _publicSharesCards
    }

}