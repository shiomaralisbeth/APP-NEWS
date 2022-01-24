package com.shiomara.appnews.domain.noticias

import com.shiomara.appnews.domain.common.TypeFactory
import com.shiomara.appnews.domain.common.Visitable
import java.io.Serializable


data class Noticia(
    val idStory:Int?=0,
    val titleStory:String?="",
    val urlStory:String?="",
    val authorStory:String?="",
    val comment:String?="",
    val createdDate:String?="",
    val isOpened: Boolean? = false
): Visitable, Serializable {
    override fun type(typeFactory: TypeFactory): Int {
        return typeFactory.type(this)
    }
}
