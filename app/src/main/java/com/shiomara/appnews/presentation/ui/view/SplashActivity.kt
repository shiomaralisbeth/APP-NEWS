package com.shiomara.appnews.presentation.ui.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.shiomara.appnews.R

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({ init() }, 2500)
    }

    private fun init() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}