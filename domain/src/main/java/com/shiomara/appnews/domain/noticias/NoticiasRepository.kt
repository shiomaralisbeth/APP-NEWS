package com.shiomara.appnews.domain.noticias

import com.shiomara.appnews.domain.common.Result

interface NoticiasRepository {
    suspend fun getNoticiaList(): Result<List<Noticia>>

    suspend fun getNoticiaById(id: Int): Result<Noticia>
}