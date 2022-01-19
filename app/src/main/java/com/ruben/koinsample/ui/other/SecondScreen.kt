package com.ruben.koinsample.ui.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.koin.androidx.compose.getViewModel

/**
 * Created by Ruben Quadros on 19/01/22
 **/
@Composable
fun SecondScreen(
    secondViewModel: SecondViewModel = getViewModel()
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = secondViewModel.getValue(),
            color = Color.White
        )

    }
}