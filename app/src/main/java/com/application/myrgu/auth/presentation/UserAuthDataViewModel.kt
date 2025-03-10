package com.application.myrgu.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.myrgu.auth.domain.model.UserAuthData
import com.application.myrgu.auth.domain.usecase.DeleteUserAuthDataUseCase
import com.application.myrgu.auth.domain.usecase.GetUserAuthDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserAuthDataViewModel @Inject constructor(
    getUserAuthDataUseCase: GetUserAuthDataUseCase,
    private val deleteUserAuthDataUseCase: DeleteUserAuthDataUseCase,
) : ViewModel() {
    private val _userAuthData = MutableSharedFlow<UserAuthData?>(replay = 1)
    val userAuthData: SharedFlow<UserAuthData?> = _userAuthData.asSharedFlow()

    var isUserAuthDataLoaded = false

    init {
        getUserAuthDataUseCase()
            .onEach {
                _userAuthData.emit(it)
            }
            .launchIn(viewModelScope)
    }

    fun setAuthData(userAuthData: UserAuthData) {
        viewModelScope.launch {
            _userAuthData.emit(userAuthData)
        }
    }

    fun deleteAuthData() {
        viewModelScope.launch(Dispatchers.IO) {
            _userAuthData.emit(null)
            deleteUserAuthDataUseCase()
        }
    }
}