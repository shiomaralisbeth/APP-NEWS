package com.shiomara.appnews.presentation.ui.common

import com.shiomara.appnews.R
import com.shiomara.appnews.domain.common.TypeFactory
import com.shiomara.appnews.domain.noticias.Noticia

class TypeFactoryImpl : TypeFactory {
    override fun type(item: Noticia): Int {
        return R.layout.row_notice
    }
}