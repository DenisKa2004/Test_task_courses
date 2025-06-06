package com.example.test_task_courses.navigation_app

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.example.test_task_courses.uiActivity.loginActivity.LoginScreen
import com.example.test_task_courses.uiActivity.mainScreen.MainScreen
import com.example.test_task_courses.uiActivity.profileActivity.ProfileScreen
import com.example.test_task_courses.uiActivity.favouritesActivity.FavouritesScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationController(navController: NavHostController) {
    AnimatedNavHost(
        navController      = navController,
        startDestination   = Screen.Login.route,

        enterTransition    = {
            slideInHorizontally(
                initialOffsetX = { it / 2 },
                animationSpec  = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
            ) + fadeIn(tween(300)) + scaleIn(
                initialScale   = 0.95f,
                animationSpec  = tween(300, easing = FastOutSlowInEasing)
            )
        },
        exitTransition     = {
            slideOutHorizontally(
                targetOffsetX = { -it / 2 },
                animationSpec = tween(300, easing = FastOutSlowInEasing)
            ) + fadeOut(tween(300)) + scaleOut(
                targetScale   = 0.95f,
                animationSpec = tween(300, easing = FastOutSlowInEasing)
            )
        },
        popEnterTransition = {
            fadeIn(tween(200)) + scaleIn(
                initialScale  = 1.05f,
                animationSpec = tween(200)
            )
        },
        popExitTransition  = {
            fadeOut(tween(200)) + scaleOut(
                targetScale   = 1.05f,
                animationSpec = tween(200)
            )
        }
    ) {
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.Main.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Main.route)   { MainScreen(navController) }
        composable(Screen.Profile.route){ ProfileScreen(navController) }
        composable(Screen.Favourite.route){ FavouritesScreen(navController) }
    }
}
