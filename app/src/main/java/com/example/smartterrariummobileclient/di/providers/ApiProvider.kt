package com.example.smartterrariummobileclient.di.providers

import com.example.smartterrariummobileclient.di.ServerPath
import com.example.smartterrariummobileclient.model.data.server.ServerApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class ApiProvider @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson,
    @ServerPath private val serverPath: String
) : Provider<ServerApi> {

    override fun get(): ServerApi =
        Retrofit.Builder()
            .baseUrl(serverPath)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ServerApi::class.java)
}
