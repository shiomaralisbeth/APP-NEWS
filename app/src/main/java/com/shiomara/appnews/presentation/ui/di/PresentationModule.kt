package com.shiomara.appnews.presentation.ui.di

import com.shiomara.appnews.presentation.ui.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { MainActivityViewModel(get()) }
}