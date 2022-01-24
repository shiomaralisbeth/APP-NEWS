package com.shiomara.appnewsdata.noticias

import com.google.gson.annotations.SerializedName
import com.shiomara.appnewsdata.network.RoomMapper

data class NoticiaResponse(
    @SerializedName("story_id")
    val idStory:Int,
    @SerializedName("story_title")
    val titleStory:String?="",
    @SerializedName("story_url")
    val urlStory:String?="",
    @SerializedName("author")
    val authorStory:String?="",
    @SerializedName("comment_text")
    val comment:String?="",
    @SerializedName("created_at")
    val createdDate:String?=""
) : RoomMapper<NoticiaEntity> {
    override fun mapToRoomEntity(): NoticiaEntity {
        return NoticiaEntity(idStory,titleStory,urlStory,authorStory,comment,createdDate, false)
    }
}