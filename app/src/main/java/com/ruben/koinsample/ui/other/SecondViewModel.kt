package com.ruben.koinsample.ui.other

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ruben.koinsample.Destinations.PARAM

/**
 * Created by Ruben Quadros on 19/01/22
 **/
class SecondViewModel(private val handle: SavedStateHandle): ViewModel() {

    fun getValue() : String {
        return handle.get<String>(PARAM) ?: "Y null?"
    }


}