package com.ruben.koinsample.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Created by Ruben Quadros on 19/01/22
 **/
@Composable
fun HomeScreen(
    navigateToSecondScreen: (String) -> Unit
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { navigateToSecondScreen.invoke("Second Screen") }
        ) {
            Text(text = "Go to next destination")
        }
    }

}