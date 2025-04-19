package com.application.myrgu.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.myrgu.auth.domain.model.AuthRequest
import com.application.myrgu.auth.domain.model.AuthResponse
import com.application.myrgu.auth.domain.usecase.AuthUseCase
import com.application.myrgu.core.data.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _auth = MutableStateFlow<Result<AuthResponse>>(Result.Initial)
    val auth: StateFlow<Result<AuthResponse>> = _auth.asStateFlow()

    fun auth(authRequest: AuthRequest) {
        authUseCase(authRequest)
            .onEach {
                _auth.value = it
            }
            .launchIn(viewModelScope)
    }
}