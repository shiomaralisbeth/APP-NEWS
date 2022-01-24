package com.shiomara.appnews.presentation.ui.view

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shiomara.appnews.R
import com.shiomara.appnews.databinding.ActivityMainBinding
import com.shiomara.appnews.domain.noticias.Noticia
import com.shiomara.appnews.presentation.ui.adapter.NoticiasAdapter
import com.shiomara.appnews.presentation.ui.common.CellClickListener
import com.shiomara.appnews.presentation.ui.common.Loading
import com.shiomara.appnews.presentation.ui.common.NoInternetState
import com.shiomara.appnews.presentation.ui.common.Success
import com.shiomara.appnews.presentation.ui.utils.DateUtils
import com.shiomara.appnews.presentation.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.time.format.DateTimeFormatter
import java.util.*
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import com.shiomara.appnewsdata.noticias.NoticiaDao


class MainActivity : AppCompatActivity(), CellClickListener {
    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var noticiasAdapter: NoticiasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        suscribeToData()
        setUpUI()
        viewModel.getAll()
        swipeRefresh.setOnRefreshListener {
            suscribeToData()
            swipeRefresh.isRefreshing = false
        }
    }

    fun setUpUI() {
        txtFecha.text = DateUtils.getDate("yyyy-MM-dd")
        val divider = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.viewListNoticias.rcvNoticias.layoutManager = linearLayoutManager
        binding.viewListNoticias.rcvNoticias.addItemDecoration(divider)
        binding.viewListNoticias.rcvNoticias.adapter = NoticiasAdapter(this)
    }

    private fun suscribeToData() {
        viewModel.viewState.observe(this, Observer { viewState ->
            when (viewState) {
                is Loading -> binding.progress.isVisible = true
                is Success -> {
                    if (viewState.data.isNotEmpty()) {
                        binding.viewListNoticias.viewPortal.visibility = View.VISIBLE
                        binding.emptyView.viewEmptyState.visibility = View.GONE
                        showMoviesList(viewState.data)
                    } else {
                        binding.viewListNoticias.viewPortal.visibility = View.GONE
                        binding.emptyView.viewEmptyState.visibility = View.VISIBLE
                    }
                    progress.isVisible =false
                }
                is Error -> {
                    progress.isVisible =false
                    Toast.makeText(this, "intente de nuevo", Toast.LENGTH_SHORT)
                }
                is NoInternetState -> {

                }
            }
        })
    }

    private fun showMoviesList(data: List<Noticia>) {
        Log.i("data", data.toString())
        noticiasAdapter = NoticiasAdapter(this)
        binding.viewListNoticias.rcvNoticias.adapter = noticiasAdapter
        noticiasAdapter.setItems(data)
    }

    override fun onCellClickListener(noticia: Noticia) {
        val intent = Intent(this, DetailNoticiaActivity::class.java).apply {
            putExtra("URLSTORY", noticia.urlStory)
        }
        startActivity(intent)
    }
}