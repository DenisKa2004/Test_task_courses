package com.example.test_task_courses.uiActivity.profileActivity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.test_task_courses.R
import com.example.test_task_courses.navigation_app.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Профиль",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF151515)
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF24252A),
                contentColor = Color.White
            ) {
                val backStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = backStackEntry?.destination?.route

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.house),
                            contentDescription = "Главная"
                        )
                    },
                    label = { Text("Главная") },
                    selected = currentRoute == Screen.Main.route,
                    onClick = {
                        if (currentRoute != Screen.Main.route) {
                            navController.navigate(Screen.Main.route) {
                                popUpTo(Screen.Main.route) { inclusive = true }
                            }
                        }
                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color(0xFF12B956),
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color(0xFF12B956),
                        unselectedTextColor = Color.Gray,
                        selectedIndicatorColor = Color(0xFF32333A),
                        disabledIconColor = Color.Gray,
                        disabledTextColor = Color.Gray,
                    ),

                    )

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.bookmark_menu),
                            contentDescription = "Избранное"
                        )
                    },
                    label = { Text("Избранное") },
                    selected = currentRoute == Screen.Favourite.route,
                    onClick = {
                        if (currentRoute != Screen.Favourite.route) {
                            navController.navigate(Screen.Favourite.route) {
                                popUpTo(Screen.Favourite.route) { inclusive = true }
                            }
                        }
                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color(0xFF12B956),
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color(0xFF12B956),
                        unselectedTextColor = Color.Gray,
                        selectedIndicatorColor = Color(0xFF32333A),
                        disabledIconColor = Color.Gray,
                        disabledTextColor = Color.Gray,
                    ),

                    )

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.person),
                            contentDescription = "Аккаунт"
                        )
                    },
                    label = { Text("Аккаунт") },
                    selected = currentRoute == Screen.Profile.route,
                    onClick = {
                        if (currentRoute != Screen.Profile.route) {
                            navController.navigate(Screen.Profile.route) {
                                popUpTo(Screen.Profile.route) { inclusive = true }
                            }
                        }
                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color(0xFF12B956),
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color(0xFF12B956),
                        unselectedTextColor = Color.Gray,
                        selectedIndicatorColor = Color(0xFF32333A),
                        disabledIconColor = Color.Gray,
                        disabledTextColor = Color.Gray,
                    ),

                    )
            }
        },

        containerColor = Color(0xFF151515),
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(Modifier.padding(padding)) {  }
    }
}