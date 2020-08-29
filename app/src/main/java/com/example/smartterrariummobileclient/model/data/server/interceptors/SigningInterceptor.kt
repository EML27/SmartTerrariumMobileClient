package com.example.smartterrariummobileclient.model.data.server.interceptors

import com.example.smartterrariummobileclient.model.data.storage.AuthHolder
import com.example.smartterrariummobileclient.model.system.SecurityUtils
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class SigningInterceptor @Inject constructor(
    private val securityUtils: SecurityUtils,
    private val authHolder: AuthHolder
) : Interceptor {

    private val password = "1"

    override fun intercept(chain: Interceptor.Chain?): Response {
        if (chain == null) {
            throw Exception("Interceptor.Chain is null")
        }

        // We have to add basic auth to each request to server.
        // Basic auth contains login (number of a driver) and password (1).
        // 1 - is strongly constant in server, use this all time.

        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
        val token = authHolder.token
            ?: throw Exception("token wasn't written into the storage")

        val basicAuthHash = securityUtils.getBasicAuthHash(token, password).replace("\n", "")

        newRequest.addHeader("Authorization", "Bearer $token")
        newRequest.removeHeader("Accept-Encoding")

        return chain.proceed(newRequest.build())
    }
}