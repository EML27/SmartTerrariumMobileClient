package com.example.smartterrariummobileclient.model.system

import android.content.Context
import javax.inject.Inject

class ResourceManager @Inject constructor(private val context: Context) : IResourceManager {

    override fun getString(resId: Int): String = context.getString(resId)

    override fun getStringArray(resId: Int): Array<String> = context.resources.getStringArray(resId)
}