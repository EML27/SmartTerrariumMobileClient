package com.example.smartterrariummobileclient.model.data.server

sealed class Result<out R : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}