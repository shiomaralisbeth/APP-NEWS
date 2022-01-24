package com.shiomara.appnewsdata.noticias

import com.shiomara.appnews.data.network.RestApi
import com.shiomara.appnews.domain.noticias.Noticia
import com.shiomara.appnews.domain.noticias.NoticiasRepository
import com.shiomara.appnewsdata.common.Repository
import com.shiomara.appnews.domain.common.Result
import com.shiomara.appnewsdata.network.getDataAsListApi

class NoticiaRepositoryImpl(private val api: RestApi, private val noticiaDao: NoticiaDao) :
    Repository<Noticia, NoticiaEntity>(), NoticiasRepository {
    override suspend fun getNoticiaList(): Result<List<Noticia>> {
        return fetchDataList(
            apiDataProvider = {
                val datos = api.getNoticiaList()
                datos.body()?.hits?.map { it.mapToRoomEntity() }
                    ?.let { noticiaDao.insertMultiple(it) }
                datos.getDataAsListApi()
            }, dbDataProvider = {
                noticiaDao.getNoticiaList()
            }
        )
    }

    override suspend fun getNoticiaById(id: Int): Result<Noticia> {
        TODO("Not yet implemented")
    }
}