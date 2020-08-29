package com.example.smartterrariummobileclient

import android.app.Application
import com.example.smartterrariummobileclient.di.Scopes
import com.example.smartterrariummobileclient.di.modules.AppModule
import com.example.smartterrariummobileclient.di.modules.ServerModule
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initLogger()
        initToothpick()
        initAppScope()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction())
        }
    }

    private fun initAppScope() {
        val appScope = Toothpick.openScope(Scopes.APP_SCOPE)
        appScope.installModules(AppModule(this))

        val serverScope = Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.SERVER_SCOPE)
        serverScope.installModules(ServerModule(BuildConfig.API_URL))
    }
}