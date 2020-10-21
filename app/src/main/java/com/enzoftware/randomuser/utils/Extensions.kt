package com.enzoftware.randomuser.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


/**
 * Created by Enzo Lizama Paredes on 10/21/20.
 * Contact: lizama.enzo@gmail.com
 */

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>
