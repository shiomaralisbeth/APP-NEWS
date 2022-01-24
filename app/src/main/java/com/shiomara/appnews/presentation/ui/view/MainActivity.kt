package com.shiomara.appnews.presentation.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.shiomara.appnews.databinding.ActivityMainBinding
import com.shiomara.appnews.presentation.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noticiasAdapter = NoticiasAdapter { viewModel::onNoticiaClick }
        binding.viewListNoticias.rcvNoticias.adapter = noticiasAdapter

        viewModel.noticiasList.observe(this, Observer {
            noticiasAdapter.noticiaList = it
        })

        viewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })
        viewModel.onCreate()
    }
}