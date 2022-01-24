package com.shiomara.appnews.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiomara.appnews.domain.noticias.GetNoticiasList
import com.shiomara.appnews.domain.noticias.Noticia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailNoticiaViewModel() : ViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    val  isLoading: LiveData<Boolean> get() = _isLoading

    private val _urlNoticia = MutableLiveData<String>()
    val  urlNoticia: LiveData<String> get() = _urlNoticia



}