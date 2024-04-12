package com.menecheli.gistexplorer.utils

import java.text.SimpleDateFormat
import java.util.Locale

class DateFormater {
    fun formatDateYear(iso8601Date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return try {
            val date = inputFormat.parse(iso8601Date)
            outputFormat.format(date)
        } catch (e: Exception) {
            iso8601Date
        }
    }
}