package com.ruben.koinsample.ui.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel

/**
 * Created by Ruben Quadros on 19/01/22
 **/
@Composable
fun SecondScreen(
    secondViewModel: SecondViewModel = getViewModel(),
    openThirdScreen: (String) -> Unit
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = secondViewModel.getValue(),
            color = Color.White
        )

        Button(modifier = Modifier.padding(16.dp).align(Alignment.BottomCenter), onClick = { openThirdScreen.invoke("Third Screen") }) {
            Text(text = "Open")
        }

    }
}

@Composable
fun ThirdScreen(
    thirdViewModel: ThirdViewModel = getViewModel()
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = thirdViewModel.getValue(),
            color = Color.White
        )

    }
}