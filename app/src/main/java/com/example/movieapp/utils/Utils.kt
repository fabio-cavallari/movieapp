package com.example.movieapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val tmdbApiDateFormat = "yyyy-MM-dd"
const val defaultDateOutputFormat = "dd/MM/yyyy"
fun formatDate(
    inputDate: String,
    inputFormat: String = tmdbApiDateFormat,
    outputFormat: String = defaultDateOutputFormat
): String {
    return try {
        val inputDateFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
        val date: Date? = inputDateFormat.parse(inputDate)
        val outputDateFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
        outputDateFormat.format(date)
    } catch (e: Exception) {
        inputDate
    }
}