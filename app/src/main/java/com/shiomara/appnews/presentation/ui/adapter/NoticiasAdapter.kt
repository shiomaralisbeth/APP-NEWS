package com.shiomara.appnews.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiomara.appnews.R
import com.shiomara.appnews.databinding.RowNoticeBinding
import com.shiomara.appnews.domain.noticias.Noticia
import com.shiomara.appnews.presentation.ui.common.basicDiffUtil
import com.shiomara.appnews.presentation.ui.common.inflate

class NoticiasAdapter(private val listener: (Noticia) -> Unit) :
    RecyclerView.Adapter<NoticiasAdapter.ViewHolder>() {

    var noticiaList: List<Noticia> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.idStory == new.idStory }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.row_notice, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = noticiaList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = noticiaList[position]
        holder.bind(noticia)
        holder.itemView.setOnClickListener { listener(noticia) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RowNoticeBinding.bind(view)
        fun bind(noticia: Noticia) = with(binding) {
            txtTituloNoticia.text = noticia.titleStory
            txtAutor.text = noticia.authorStory
        }
    }
}