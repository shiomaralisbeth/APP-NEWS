package com.shiomara.appnewsdata.noticias

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shiomara.appnews.domain.noticias.Noticia
import com.shiomara.appnewsdata.network.DomainMapper
import org.jetbrains.annotations.NonNls

@Entity(tableName = "noticias")
data class NoticiaEntity(
    @PrimaryKey
    @NonNull
    val idStory: Int,
    val titleStory: String? = "",
    val urlStory: String? = "",
    val authorStory: String? = "",
    val comment: String? = "",
    val createdDate: String? = "",
    val isOpened: Boolean? = false
) : DomainMapper<Noticia> {
    override fun mapToDomainModel(): Noticia {
        return Noticia(idStory, titleStory, urlStory, authorStory, comment, createdDate, isOpened)
    }
}