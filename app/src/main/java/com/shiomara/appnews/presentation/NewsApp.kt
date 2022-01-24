package com.shiomara.appnews.presentation

import android.app.Application
import com.shiomara.appnews.domain.di.interactionModule
import com.shiomara.appnewsdata.di.databaseModule
import com.shiomara.appnewsdata.di.networkModule
import com.shiomara.appnewsdata.di.repositoryModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApp: Application() {

    val domainModules = listOf(interactionModule)
    val dataModules = listOf(databaseModule, networkModule, repositoryModule)


    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)
            androidContext(this@NewsApp)
            modules(domainModules + dataModules)
        }
    }
}