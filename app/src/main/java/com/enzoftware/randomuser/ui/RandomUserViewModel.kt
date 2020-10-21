package com.enzoftware.randomuser.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enzoftware.randomuser.domain.usecase.FetchRandomUserUseCase
import com.enzoftware.randomuser.utils.CallResult
import com.enzoftware.randomuser.utils.DispatchersUtil
import com.enzoftware.randomuser.utils.UIViewState
import com.enzoftware.randomuser.utils.asLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by Enzo Lizama Paredes on 10/20/20.
 * Contact: lizama.enzo@gmail.com
 */

class RandomUserViewModel @ViewModelInject constructor(
    private val useCase: FetchRandomUserUseCase,
    private val dispatchers: DispatchersUtil
) : ViewModel() {
    private val _mutableUIViewState = MutableLiveData<UIViewState<Any>>()

    val uiViewStateObservable = _mutableUIViewState.asLiveData()

    fun fetchRandomUser() {
        emitUIState(UIViewState.Loading)
        viewModelScope.launch(dispatchers.io) {
            val result = useCase.fetchRandomUser()
            withContext(dispatchers.main) {
                if (result is CallResult.Success) {
                    emitUIState(UIViewState.Success(result.data))
                } else if (result is CallResult.Error) {
                    emitUIState(UIViewState.Error(result.exception.message!!))
                }
            }
        }
    }

    private fun emitUIState(state: UIViewState<Any>) {
        _mutableUIViewState.postValue(state)
    }

}