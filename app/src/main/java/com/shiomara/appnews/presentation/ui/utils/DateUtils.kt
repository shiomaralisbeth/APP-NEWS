package com.shiomara.appnews.presentation.ui.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getDate(pattern: String? = "yyyy-MM-dd"): String {
        val dateFormat: DateFormat = SimpleDateFormat(pattern, Locale.US)
        val date = Date()
        return dateFormat.format(date)
    }
}