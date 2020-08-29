package com.example.smartterrariummobileclient.model.data.server

import com.google.gson.annotations.SerializedName

data class ApiErrorResponse(
    @SerializedName("message")
    val message: String
)
