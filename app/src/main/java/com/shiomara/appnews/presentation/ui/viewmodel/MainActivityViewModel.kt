package com.shiomara.appnews.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel() : ViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    val  isLoading: LiveData<Boolean> get() = _isLoading

    private val _noticias = MutableLiveData<List<com.shiomara.appnews.domain.noticias.Noticia>>()
    val  noticiasList: LiveData<List<com.shiomara.appnews.domain.noticias.Noticia>> get() = _noticias


    fun onCreate() {
        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true
            //_noticias.value = getNoticias.invoke()
            _isLoading.value = false
        }
    }

    fun onNoticiaClick(noticia: com.shiomara.appnews.domain.noticias.Noticia){

    }
}