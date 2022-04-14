package com.ruben.koinsample

import android.app.Application
import com.ruben.koinsample.ui.other.SecondViewModel
import com.ruben.koinsample.ui.other.ThirdViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 19/01/22
 **/
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { modules(listOf(appModule)) }
    }
}

val appModule = module {
    viewModel { SecondViewModel(get()) }
    viewModel { ThirdViewModel(get()) }
}