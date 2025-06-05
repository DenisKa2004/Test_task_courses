package com.example.test_task_courses.uiActivity.loginActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: LoginUseCase
) : ViewModel() {

    // Поля email и password
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    // Флаг валидности формы
    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid.asStateFlow()

    // Событие «успешный логин»
    private val _loginEvent = MutableSharedFlow<Unit>(replay = 0)
    val loginEvent: SharedFlow<Unit> = _loginEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            combine(_email, _password) { e, p ->
                isEmailValid(e) && p.isNotBlank()
            }.collect { valid ->
                _isFormValid.value = valid
            }
        }
    }

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClicked() {
        if (!_isFormValid.value) return

        viewModelScope.launch {
            val success = authUseCase.login(_email.value, _password.value)
            if (success) {
                _loginEvent.emit(Unit)
            } else {
                // Можно добавить логику вывода ошибки
            }
        }
    }
    fun onNoValidClicked() {
        if (!_isFormValid.value) return

        viewModelScope.launch {
            val success = authUseCase.login(_email.value, _password.value)
            if (success) {
                _loginEvent.emit(Unit)
            } else {
                // Можно добавить логику вывода ошибки
            }
        }
    }

    private fun isEmailValid(input: String): Boolean {
        val emailPattern = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        if (input.any { it in 'А'..'я' }) return false
        return emailPattern.matches(input)
    }
}