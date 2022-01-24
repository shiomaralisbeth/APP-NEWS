package com.shiomara.appnewsdata.noticias

import com.shiomara.appnewsdata.network.RoomMapper

class ApiResponse <T> (
    val hits:List<T>,
    val nbHits:Int?=0,
    val page:Int?=0,
    val nbPages:Int?=0,
    val hitsPerPage:Int?=0,
    val exhaustiveNbHits:Boolean,
    val exhaustiveTypo:Boolean,
    val query:String?="",
    val params:String?="",
    val renderingContent:List<T>,
    val processingTimeMS:Int?=0
): RoomMapper<List<T>> {
    override fun mapToRoomEntity(): List<T> {
        return hits
    }
}