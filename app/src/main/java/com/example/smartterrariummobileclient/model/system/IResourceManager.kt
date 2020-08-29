package com.example.smartterrariummobileclient.model.system

interface IResourceManager {
    fun getString(resId: Int): String

    fun getStringArray(resId: Int): Array<String>
}