package com.enzoftware.randomuser.utils

import java.io.IOException


/**
 * Created by Enzo Lizama Paredes on 10/20/20.
 * Contact: lizama.enzo@gmail.com
 */

sealed class CallResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : CallResult<T>()
    data class Error(val exception: Exception) : CallResult<Nothing>()
}

suspend fun <T : Any> safeApiCall(
    call: suspend () -> CallResult<T>,
    errorMessage: String
): CallResult<T> {
    return try {
        call()
    } catch (e: Exception) {
        CallResult.Error(IOException(errorMessage, e))
    }
}