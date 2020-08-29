package com.example.smartterrariummobileclient.extensions

fun String?.emptyIfNull(): String {
    return if (this == "null" || this == "Null") {
        ""
    } else this ?: ""
}
