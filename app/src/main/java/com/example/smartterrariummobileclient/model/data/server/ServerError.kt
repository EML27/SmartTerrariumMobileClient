package com.example.smartterrariummobileclient.model.data.server

import java.lang.RuntimeException

class ServerError(
    val errorCode: Int,
    val errorResponse: ApiErrorResponse?
) : RuntimeException()
