package com.example.smartterrariummobileclient.model.interactors

import com.example.smartterrariummobileclient.extensions.tryOrNull
import com.example.smartterrariummobileclient.model.data.server.ApiErrorResponse
import com.example.smartterrariummobileclient.model.data.server.ServerError
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import com.example.smartterrariummobileclient.model.data.server.Result

abstract class BaseInteractor {
    /**
     * Call server api method from Retrofit API Interface. Works with suspend functions.
     * @param dispatcher Dispatcher for suspend function calling
     * @param apiCall lambda for suspend function
     * @return Result.Success<T> or Result.Error in case of error api response.
     * @see Result
     */
    protected suspend fun <T : Any> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): Result<T> {
        return withContext(dispatcher) {
            try {
                Result.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> Result.Error(throwable)
                    is HttpException -> {
                        val serverError = convertErrorBody(throwable)
                        Result.Error(serverError)
                    }
                    else -> {
                        Result.Error(throwable)
                    }
                }
            }
        }
    }

    /**
     * Read error body from HttpException and returns ServerError.
     * Using GSON to convert json body to class instance.
     * @param throwable [HttpException] from okHttp
     * @return [ServerError]
     */
    private fun convertErrorBody(throwable: HttpException): ServerError {
        try {
            throwable.response()?.errorBody()?.source()?.let {
                val code = throwable.code()
                val resString = it.readUtf8()
                val response = tryOrNull {
                    Gson().fromJson(resString, ApiErrorResponse::class.java)
                }
                return ServerError(code, response)
            }

            return ServerError(throwable.code(), null)
        } catch (exception: Exception) {
            return ServerError(throwable.code(), null)
        }
    }
}