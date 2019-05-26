package omarbradley.com.util.date

import com.soywiz.klock.milliseconds
import com.soywiz.klock.toTimeString

fun Long.convertToHHmmssFormat(): String =
    milliseconds.toTimeString(3)
