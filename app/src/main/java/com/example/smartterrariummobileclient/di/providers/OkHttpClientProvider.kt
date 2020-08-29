package com.example.smartterrariummobileclient.di.providers

import com.example.smartterrariummobileclient.BuildConfig
import com.example.smartterrariummobileclient.model.system.SecurityUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.example.smartterrariummobileclient.model.data.server.interceptors.CurlLoggingInterceptor
import com.example.smartterrariummobileclient.model.data.server.interceptors.SigningInterceptor
import com.example.smartterrariummobileclient.model.data.storage.AuthHolder
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

/**
 * Project Truck Crew
 * Package ru.telecor.gm.mobile.droid.di.providers
 *
 * Provides OkHttpClient instance.
 *
 * Created by Artem Skopincev (aka sharpyx) 27.07.2020
 * Copyright © 2020 TKOInform. All rights reserved.
 */
class OkHttpClientProvider @Inject constructor(
    private val securityUtils: SecurityUtils,
    private val authHolder: AuthHolder
) : Provider<OkHttpClient> {

    private val connectTimeout = 30L
    private val readTimeout = 30L

    override fun get(): OkHttpClient  = with(OkHttpClient.Builder()) {
        connectTimeout(connectTimeout, TimeUnit.SECONDS)
        readTimeout(readTimeout, TimeUnit.SECONDS)

        // interceptor for signing requests to server
        addNetworkInterceptor(SigningInterceptor(securityUtils, authHolder))
//        addNetworkInterceptor(ErrorInterceptor())

        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addNetworkInterceptor(CurlLoggingInterceptor())
        }

        build()
    }
}