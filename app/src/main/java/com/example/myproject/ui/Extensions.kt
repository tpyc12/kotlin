package com.example.myproject.ui

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.myproject.R
import com.example.myproject.model.Color
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"

fun Date.format(): String = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(this)

fun Color.getColorInt(context: Context): Int =
    ContextCompat.getColor(context, getColorRes())

fun Color.getColorRes(): Int = when (this) {
    Color.WHITE -> R.color.color_white
    Color.VIOLET -> R.color.color_violet
    Color.BLUE -> R.color.color_blue
    Color.GREEN -> R.color.color_green
    Color.PINK -> R.color.color_pink
    Color.RED -> R.color.color_red
    Color.YELLOW -> R.color.color_yellow
}

fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()