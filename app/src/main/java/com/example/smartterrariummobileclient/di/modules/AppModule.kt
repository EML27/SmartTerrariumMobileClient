package com.example.smartterrariummobileclient.di.modules

import android.content.Context
import android.provider.Settings
import com.example.smartterrariummobileclient.BuildConfig
import com.example.smartterrariummobileclient.model.system.AppSystemInfo
import com.example.smartterrariummobileclient.model.system.IResourceManager
import com.example.smartterrariummobileclient.model.system.ResourceManager
import toothpick.config.Module

class AppModule(private val context: Context) : Module() {

    init {
        bind(Context::class.java).toInstance(context)
        bind(IResourceManager::class.java).toInstance(ResourceManager(context))

        bind(AppSystemInfo::class.java).toInstance(
            AppSystemInfo(
                BuildConfig.APP_NAME,
                BuildConfig.VERSION_NAME,
                BuildConfig.VERSION_CODE,
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            )
        )
    }
}