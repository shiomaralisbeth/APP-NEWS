package com.shiomara.appnews.presentation.ui.common

import com.shiomara.appnews.domain.noticias.Noticia

interface CellClickListener {
    fun onCellClickListener(noticia: Noticia)
}