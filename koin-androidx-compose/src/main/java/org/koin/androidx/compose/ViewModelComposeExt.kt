/*
 * Copyright 2017-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.koin.androidx.compose

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.savedstate.SavedStateRegistryOwner
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.ext.android.getViewModelFactory
import org.koin.androidx.viewmodel.scope.BundleDefinition
import org.koin.androidx.viewmodel.scope.emptyState
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

/**
 * Resolve ViewModel instance
 *
 * @param qualifier
 * @param parameters
 *
 * @author Arnaud Giuliani
 */
@OptIn(KoinInternalApi::class)
@Composable
inline fun <reified T : ViewModel> getViewModel(
    qualifier: Qualifier? = null,
    owner: ViewModelStoreOwner? = null,
    scope: Scope = GlobalContext.get().scopeRegistry.rootScope,
    noinline parameters: ParametersDefinition? = null,
): T {
    return viewModel<T>(qualifier, owner, scope, parameters).value
}

/**
 * Retrieve ViewModelOwner for current LocalViewModelStoreOwner & LocalSavedStateRegistryOwner
 *
 * @return ViewModelOwner
 */
@Composable
fun getComposeViewModelOwner(): ViewModelOwner {
    return ViewModelOwner.fromCompose(
        LocalViewModelStoreOwner.current!!,
        LocalSavedStateRegistryOwner.current
    )
}

@OptIn(KoinInternalApi::class)
@Composable
inline fun <reified T : ViewModel> viewModel(
    qualifier: Qualifier? = null,
    owner: ViewModelStoreOwner? = null,
    scope: Scope = GlobalContext.get().scopeRegistry.rootScope,
    noinline parameters: ParametersDefinition? = null,
): Lazy<T> {
    val storeOwner = owner?.let { ViewModelOwner.fromCompose(it) } ?: getComposeViewModelOwner()
    val state = (owner as? NavBackStackEntry)?.arguments ?: storeOwner.defaultArgs
    return remember(qualifier, parameters) {
        ViewModelLazy(T::class, { storeOwner.storeOwner.viewModelStore }){
            getViewModelFactory<T>(
                owner = {storeOwner},
                qualifier = qualifier,
                parameters = parameters,
                scope = scope,
                state = state?.let { {it} } ?: emptyState()
            )
        }
    }
}

/**
 * Resolve ViewModel instance
 *
 * @param qualifier
 * @param parameters
 *
 * @author Arnaud Giuliani
 */
@OptIn(KoinInternalApi::class)
@Composable
@Deprecated("getStateViewModel will be merged to sharedViewModel - no need anymore of state parameter")
inline fun <reified T : ViewModel> getStateViewModel(
    qualifier: Qualifier? = null,
    owner: ViewModelStoreOwner? = null,
    noinline state: BundleDefinition = emptyState(),
    scope: Scope = GlobalContext.get().scopeRegistry.rootScope,
    noinline parameters: ParametersDefinition? = null,
): T {
    val storeOwner = owner?.let { ViewModelOwner.from(it) } ?: getComposeViewModelOwner()
    return remember(qualifier, parameters) {
        ViewModelLazy(T::class, { storeOwner.storeOwner.viewModelStore }){
            getViewModelFactory<T>({storeOwner},qualifier,parameters, state,scope)
        }.value
    }
}

/**
 * @param storeOwner
 * @param stateRegistry
 *
 * @author Ruben Quadros
 */
fun ViewModelOwner.Companion.fromCompose(
    storeOwner: ViewModelStoreOwner,
    stateRegistry: SavedStateRegistryOwner? = null
) = ViewModelOwner(
    storeOwner = storeOwner,
    stateRegistry = stateRegistry,
    defaultArgs = (storeOwner as? NavBackStackEntry)?.arguments
)

/**
 * @param storeOwner
 *
 * @author Ruben Quadros
 */
fun ViewModelOwner.Companion.fromCompose(
    storeOwner: ViewModelStoreOwner
) = ViewModelOwner(
    storeOwner = storeOwner,
    defaultArgs = (storeOwner as? NavBackStackEntry)?.arguments
)