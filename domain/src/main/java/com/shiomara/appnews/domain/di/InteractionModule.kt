package com.shiomara.appnews.domain.di

import com.shiomara.appnews.domain.noticias.GetNoticiasList
import com.shiomara.appnews.domain.noticias.GetNoticiasListImpl
import org.koin.dsl.module

val interactionModule = module {

    factory<GetNoticiasList> { GetNoticiasListImpl(get()) }
}