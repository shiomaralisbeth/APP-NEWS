package com.shiomara.appnewsdata.di

import androidx.room.Room
import com.shiomara.appnewsdata.common.DB_NAME
import com.shiomara.appnewsdata.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration().build()
    }

    single { get<AppDatabase>().noticiaDao() }
}