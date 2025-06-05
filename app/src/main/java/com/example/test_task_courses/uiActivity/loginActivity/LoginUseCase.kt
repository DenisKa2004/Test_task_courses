package com.example.test_task_courses.uiActivity.loginActivity

import jakarta.inject.Inject


class LoginUseCase @Inject constructor() {
    suspend fun login(email: String, password: String): Boolean {
        // Здесь можно добавить логику реальной авторизации.
        return true
    }
}