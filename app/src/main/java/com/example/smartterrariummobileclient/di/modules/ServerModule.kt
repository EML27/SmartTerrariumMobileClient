package com.example.smartterrariummobileclient.di.modules

import com.example.smartterrariummobileclient.di.ServerPath
import com.example.smartterrariummobileclient.di.providers.ApiProvider
import com.example.smartterrariummobileclient.di.providers.GsonProvider
import com.example.smartterrariummobileclient.di.providers.OkHttpClientProvider
import com.example.smartterrariummobileclient.model.data.server.ServerApi
import com.example.smartterrariummobileclient.model.interactors.ApiInteractor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import toothpick.config.Module

class ServerModule(serverUri: String) : Module() {

    init {
        bind(String::class.java).withName(ServerPath::class.java).toInstance(serverUri)
        bind(Gson::class.java).toProvider(GsonProvider::class.java).providesSingleton()
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java)
            .providesSingleton()
        bind(ServerApi::class.java).toProvider(ApiProvider::class.java).providesSingleton()

        bind(ApiInteractor::class.java).singleton()
    }
}