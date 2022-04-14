package com.ruben.koinsample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ruben.koinsample.Destinations.HOME
import com.ruben.koinsample.Destinations.PARAM
import com.ruben.koinsample.Destinations.SECOND_SCREEN
import com.ruben.koinsample.Destinations.THIRD_SCREEN
import com.ruben.koinsample.ui.home.HomeScreen
import com.ruben.koinsample.ui.other.SecondScreen
import com.ruben.koinsample.ui.other.ThirdScreen

/**
 * Created by Ruben Quadros on 19/01/22
 **/
@Composable
fun SampleApp() {
    val navController = rememberNavController()
    val navGraph  = remember(navController) { NavGraph(navController) }

    NavHost(navController = navController, startDestination = HOME) {
        composable(route = HOME) {
            HomeScreen(navigateToSecondScreen = navGraph.openSecondScreen)
        }

        composable(
            route = "$SECOND_SCREEN/{$PARAM}",
            arguments = listOf(
                navArgument(PARAM) { type = NavType.StringType }
            )
        ) {
            SecondScreen(openThirdScreen = navGraph.openThirdScreen)
        }

        composable(
            route = "$THIRD_SCREEN/{$PARAM}",
            arguments = listOf(
                navArgument(PARAM) { type = NavType.StringType }
            )
        ) {
            ThirdScreen()
        }
    }

}