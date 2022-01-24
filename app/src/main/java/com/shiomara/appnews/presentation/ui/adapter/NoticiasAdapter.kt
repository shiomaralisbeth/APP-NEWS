package com.shiomara.appnews.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.shiomara.appnews.R
import com.shiomara.appnews.databinding.RowNoticeBinding
import com.shiomara.appnews.domain.common.TypeFactory
import com.shiomara.appnews.domain.noticias.Noticia
import com.shiomara.appnews.presentation.ui.common.CellClickListener
import com.shiomara.appnews.presentation.ui.common.TypeFactoryImpl
import com.shiomara.appnews.presentation.ui.common.basicDiffUtil
import com.shiomara.appnews.presentation.ui.common.inflate
import com.shiomara.appnewsdata.noticias.NoticiaDao
import org.koin.core.KoinApplication.Companion.init

class NoticiasAdapter(private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<NoticiasAdapter.ViewHolder>() {

    private val typeFactory: TypeFactory = TypeFactoryImpl()
    private var noticiaList: List<Noticia> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.row_notice, false)
        return ViewHolder(view,cellClickListener)
    }

    override fun getItemCount(): Int = noticiaList.size

    override fun getItemViewType(position: Int): Int {
        return noticiaList[position].type(typeFactory)
    }

    fun setItems(items: List<Noticia>) {
        noticiaList = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(noticiaList[position])
        holder.itemView.isClickable= true
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(noticiaList[position])
        }

    }

    class ViewHolder(view: View, private val cellClickListener: CellClickListener) : RecyclerView.ViewHolder(view) {

        private val binding = RowNoticeBinding.bind(view)
        fun bind(noticia: Noticia) = with(binding) {
            txtTituloNoticia.text = noticia.titleStory
            txtAutor.text = noticia.authorStory
            noticiaContainer.setOnClickListener {
                cellClickListener.onCellClickListener(noticia)
            }
        }


    }
}