package com.shapeapp.shape.data.repositories

import androidx.lifecycle.LiveData
import com.shapeapp.shape.data.database.entities.Card

/**
 * Defines repository for [Card]s
 */
interface CardRepository {
    //  TODO: use DI with Kodein and "with singleton"

    fun getOfficialCards(): LiveData<List<Card>>
    fun getNewCards(): LiveData<List<Card>>
    fun getLatestCards(): LiveData<List<Card>>
    fun getPublicSharesCards(): LiveData<List<Card>>

}
