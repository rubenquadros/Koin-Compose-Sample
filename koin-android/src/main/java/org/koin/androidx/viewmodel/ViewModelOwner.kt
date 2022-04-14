package org.koin.androidx.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner

//TODO Deprecate in 3.2
typealias ViewModelOwnerDefinition = () -> ViewModelOwner
class ViewModelOwner(
        val storeOwner: ViewModelStoreOwner,
        val stateRegistry: SavedStateRegistryOwner? = null,
        val defaultArgs: Bundle? = null
) {
    companion object {
        fun from(storeOwner: ViewModelStoreOwner, stateRegistry: SavedStateRegistryOwner? = null) = ViewModelOwner(storeOwner,stateRegistry)

        fun from(storeOwner: ViewModelStoreOwner) = ViewModelOwner(storeOwner)

        fun fromAny(owner: Any) = ViewModelOwner((owner as ViewModelStoreOwner), owner as? SavedStateRegistryOwner)
    }
}