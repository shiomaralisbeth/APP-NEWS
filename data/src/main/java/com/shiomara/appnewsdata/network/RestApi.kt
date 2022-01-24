package com.shiomara.appnews.data.network

import com.shiomara.appnews.domain.noticias.Noticia
import com.shiomara.appnewsdata.noticias.ApiResponse
import com.shiomara.appnewsdata.noticias.NoticiaResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestApi {
    @GET("search_by_date?query=mobile")
    suspend fun getNoticiaList(): Response<ApiResponse<NoticiaResponse>>
}