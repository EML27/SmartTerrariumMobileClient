package com.example.smartterrariummobileclient.model.data.server.interceptors

import com.example.smartterrariummobileclient.model.data.server.ApiErrorResponse
import com.example.smartterrariummobileclient.model.data.server.ServerError
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code() in 400..500) {
            val errorResponse: ApiErrorResponse = if (response.body() != null) {
                val body = response.body()!!.string()
                Gson().fromJson<ApiErrorResponse>(body,
                    ApiErrorResponse::class.java)
            } else {
                ApiErrorResponse("bad request")
            }

            throw ServerError(response.code(), errorResponse)
        }

        return response
    }
}