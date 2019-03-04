package com.shapeapp.shape.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.fragments.PublicFragment

/**
 * [ViewModel] for [PublicFragment]
 */
class PublicFragmentViewModel : ViewModel() {

    //  TODO: clean
    //  TODO: there is no real repo (fake data is loaded)

    companion object {
        val fakeAnimals = listOf(
            "akita",
            "bat",
            "cat",
            "dog",
            "eagle",
            "fennec",
            "goat",
            "hamster",
            "iguana",
            "jaguar",
            "kangaroo",
            "lion",
            "mole",
            "newfoundland",
            "ocelot",
            "parrot",
            "quokka",
            "rabbit",
            "seal",
            "turkey",
            "uakari",
            "vulture",
            "wasp",
            "x-ray tetra",
            "yak",
            "zebra"
        )
    }

    private val officialCardsData = MutableLiveData<List<String>>()

    init {
        officialCardsData.value = fakeAnimals
    }


    fun getOfficialCardsData(): LiveData<List<String>> {
        return officialCardsData
    }

}