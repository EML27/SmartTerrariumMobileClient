package com.example.smartterrariummobileclient.model.system

import android.util.Base64
import javax.inject.Inject

class SecurityUtils @Inject constructor() {

    fun getBasicAuthHash(login: String, password: String): String {
        val authStr = "$login:$password"
        val bytes = authStr.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }
}