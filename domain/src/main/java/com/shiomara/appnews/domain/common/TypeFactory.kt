package com.shiomara.appnews.domain.common

import com.shiomara.appnews.domain.noticias.Noticia

interface TypeFactory {
    fun type(item: Noticia): Int
}