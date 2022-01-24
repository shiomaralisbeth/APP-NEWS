package com.shiomara.appnewsdata.di

import com.shiomara.appnews.domain.noticias.NoticiasRepository
import com.shiomara.appnewsdata.noticias.NoticiaRepositoryImpl
import com.shiomara.appnewsdata.utils.LocalStorage
import com.shiomara.appnewsdata.utils.NetworkUtil
import com.shiomara.appnewsdata.utils.NetworkUtilImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalStorage(androidContext()) }

    factory<NetworkUtil> { NetworkUtilImpl(androidContext()) }

    factory<NoticiasRepository> { NoticiaRepositoryImpl(get(), get()) }
}