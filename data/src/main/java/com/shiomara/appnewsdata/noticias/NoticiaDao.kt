package com.shiomara.appnewsdata.noticias

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoticiaDao {
    @Query("select * from noticias")
    suspend fun getNoticiaList(): List<NoticiaEntity>

    //@Query("select urlStory from noticias where idStory = :idStory")
    //suspend fun getNoticiaUrl(idStory: Int): NoticiaEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noticiaEntity: NoticiaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(noticiaList: List<NoticiaEntity>)
}