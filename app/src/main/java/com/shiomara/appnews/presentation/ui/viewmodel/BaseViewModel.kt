package com.shiomara.appnews.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiomara.appnews.presentation.ui.common.Loading
import com.shiomara.appnews.presentation.ui.common.ViewState
import com.shiomara.appnewsdata.utils.NetworkUtil
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel<T : Any, E> : ViewModel(), KoinComponent {
    private val networkUtils: NetworkUtil by inject()

    protected val state = MutableLiveData<ViewState<T>>()
    val viewState: LiveData<ViewState<T>> get() = state

    protected val effects = MutableLiveData<E>()
    val viewEffects: LiveData<E> get() = effects

    protected fun executeUseCase(action: suspend () -> Unit, noInternetAction: () -> Unit) {
        state.value = Loading()
        if (networkUtils.hasNetworkAccess()) {
            viewModelScope.launch { action() }
        } else {
            noInternetAction()
        }
    }

    protected fun executeUseCase(action: suspend () -> Unit) {
        state.value = Loading()
        viewModelScope.launch { action() }
    }
}