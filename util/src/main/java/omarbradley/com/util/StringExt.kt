package omarbradley.com.util

fun String.splitBar() =
    split("|").filterNot(String::isNullOrBlank)
