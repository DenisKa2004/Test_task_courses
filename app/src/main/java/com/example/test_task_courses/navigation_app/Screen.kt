package com.example.test_task_courses.navigation_app

sealed class Screen(val route:String) {
    object Login : Screen("login")
    object Main : Screen("main")
    object Favourite : Screen("favourite")
    object Profile : Screen("profile")
}