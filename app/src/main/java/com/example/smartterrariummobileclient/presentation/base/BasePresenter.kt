package com.example.smartterrariummobileclient.presentation.base

import com.example.smartterrariummobileclient.R
import moxy.MvpPresenter
import java.net.ConnectException
import java.net.SocketTimeoutException
import com.example.smartterrariummobileclient.model.data.server.Result
import com.example.smartterrariummobileclient.model.data.server.ServerError
import com.example.smartterrariummobileclient.model.system.IResourceManager

open class BasePresenter<V : BaseView> : MvpPresenter<V>() {

    /**
     * Handle result of server response or another source.
     * @param result Result from server or another source
     * @param success Execute if Result is Result.Success
     * @param error Execute if Result is Result.Error
     */
    protected fun <T : Any> handleResult(result: Result<T>, success: (success: Result.Success<T>) -> Unit,
                                         error: (error: Result.Error) -> Unit) {
        if (result is Result.Success) {
            success(result)
        } else if (result is Result.Error) {
            error(result)
        }
    }

    protected fun handleError(error: Result.Error, rm: IResourceManager) {
        when(error.exception) {
            is ConnectException -> viewState.showMessage(rm.getString(R.string.error_connection_failed))
            is SocketTimeoutException -> viewState.showMessage(rm.getString(R.string.error_connection_timeout))
            is ServerError -> viewState.showMessage(error.exception.errorResponse?.message ?: rm.getString(R.string.error_base))
            else -> viewState.showMessage(error.exception.message
                ?: rm.getString(R.string.error_base))
        }
    }

    protected fun handleError2(error: Result.Error, rm: IResourceManager, block: (message: String) -> Unit) {
        val msg = when(error.exception) {
            is ConnectException -> rm.getString(R.string.error_connection_failed)
            is SocketTimeoutException -> rm.getString(R.string.error_connection_timeout)
            is ServerError -> error.exception.errorResponse?.message ?: rm.getString(R.string.error_base)
            else -> error.exception.message ?: rm.getString(R.string.error_base)
        }

        block(msg)
    }
}