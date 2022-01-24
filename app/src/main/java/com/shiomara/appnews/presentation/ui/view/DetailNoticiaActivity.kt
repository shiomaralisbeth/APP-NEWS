package com.shiomara.appnews.presentation.ui.view

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.shiomara.appnews.R
import com.shiomara.appnews.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detalle_noticia.*

class DetailNoticiaActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detalle_noticia)
        val extras = intent.extras
        val url = extras?.getString(URLSTORY)

        setUI(url)

    }

    private fun setUI(url: String?=""){
        if (url != null) {
            webview.loadUrl(url)
        }
    }
    companion object {
        const val URLSTORY = "URLSTORY"
    }
}