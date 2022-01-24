package com.shiomara.appnews.presentation.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.shiomara.appnews.domain.noticias.Noticia

class GenericViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Noticia) = with(binding) {

    }
}