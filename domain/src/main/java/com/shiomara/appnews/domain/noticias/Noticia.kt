package com.shiomara.appnews.domain.noticias


data class Noticia(
    val idStory:Int?=0,
    val titleStory:String?="",
    val urlStory:String?="",
    val authorStory:String?="",
    val comment:String?="",
    val createdDate:String?="",
    val isOpened: Boolean? = false
)
