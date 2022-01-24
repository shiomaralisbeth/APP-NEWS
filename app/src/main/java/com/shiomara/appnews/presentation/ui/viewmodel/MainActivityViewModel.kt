package com.shiomara.appnews.presentation.ui.viewmodel

import com.shiomara.appnews.domain.common.onFailure
import com.shiomara.appnews.domain.common.onSuccess
import com.shiomara.appnews.domain.noticias.GetNoticiasList
import com.shiomara.appnews.domain.noticias.Noticia
import com.shiomara.appnews.presentation.ui.common.Success
import com.shiomara.appnews.presentation.ui.common.Error

class MainActivityViewModel(private val getNoticiasList: GetNoticiasList) :
    BaseViewModel<List<Noticia>, Any>() {
    fun getAll() = executeUseCase {
        getNoticiasList()
            .onSuccess { state.value = Success(it) }
            .onFailure { state.value = Error(it.throwable) }
    }
}