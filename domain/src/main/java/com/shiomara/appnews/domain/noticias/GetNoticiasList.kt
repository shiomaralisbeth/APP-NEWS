package com.shiomara.appnews.domain.noticias


import com.shiomara.appnews.domain.common.Result

interface GetNoticiasList{
    suspend operator fun invoke(): Result<List<Noticia>>
}

class GetNoticiasListImpl(private val noticiaRepository: NoticiasRepository): GetNoticiasList{
    override suspend fun invoke(): Result<List<Noticia>> {
        return noticiaRepository.getNoticiaList()
    }
}