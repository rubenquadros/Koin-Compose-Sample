package com.ruben.koinsample

import androidx.navigation.NavHostController
import com.ruben.koinsample.Destinations.SECOND_SCREEN
import com.ruben.koinsample.Destinations.THIRD_SCREEN

/**
 * Created by Ruben Quadros on 19/01/22
 **/
object Destinations {
    const val HOME = "home"
    const val SECOND_SCREEN = "second_screen"
    const val THIRD_SCREEN = "third_screen"
    const val PARAM = "param"
}

class NavGraph(navHostController: NavHostController) {

    val openSecondScreen: (param: String) -> Unit = { param ->
        navHostController.navigate("$SECOND_SCREEN/$param")
    }

    val openThirdScreen: (param: String) -> Unit = { param ->
        navHostController.navigate("$THIRD_SCREEN/$param")
    }
}