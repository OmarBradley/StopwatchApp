package omarbradley.com.util.date

import com.soywiz.klock.milliseconds
import com.soywiz.klock.toTimeString

val Long.HHmmssFormatString
    get() = milliseconds.toTimeString(3, true)