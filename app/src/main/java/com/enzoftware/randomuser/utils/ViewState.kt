package com.enzoftware.randomuser.utils


/**
 * Created by Enzo Lizama Paredes on 10/21/20.
 * Contact: lizama.enzo@gmail.com
 */

sealed class UIViewState<out T : Any> {
    data class Success<out T : Any>(val result: T) : UIViewState<T>()
    data class Error(val message: String) : UIViewState<Nothing>()
    object Loading : UIViewState<Nothing>()
}
