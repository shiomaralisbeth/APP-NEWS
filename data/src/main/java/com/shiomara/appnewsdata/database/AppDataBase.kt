package com.shiomara.appnewsdata.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shiomara.appnewsdata.noticias.NoticiaDao
import com.shiomara.appnewsdata.noticias.NoticiaEntity

@Database(entities = [NoticiaEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noticiaDao(): NoticiaDao
}