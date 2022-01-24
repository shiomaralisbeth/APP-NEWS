package com.shiomara.appnews.presentation.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
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
        setUpWebView()
        setUI(url)

    }

    private fun setUI(url: String?=""){
        if (url != null) {
            webview.loadUrl(url)
        }
    }

    private fun setUpWebView() {

        val webSettings = webview.settings

        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        webSettings.setAppCacheEnabled(true)
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT

        webSettings.setGeolocationEnabled(true)

        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                webview_progressbar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                webview_progressbar.visibility = View.GONE
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            }
        }

        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                webview_progressbar.progress = newProgress
            }
        }
    }

    companion object {
        const val URLSTORY = "URLSTORY"
    }
}