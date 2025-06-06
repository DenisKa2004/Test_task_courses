package com.example.test_task_courses.uiActivity.favouritesActivity

import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.test_task_courses.R
import com.example.test_task_courses.navigation_app.Screen
import com.example.test_task_courses.uiActivity.mainScreen.CourseItem
import com.example.test_task_courses.uiActivity.mainScreen.MainScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    navController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val courses by viewModel.courses.collectAsState()
    val favourites = courses.filter { it.hasLike }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Избранное",
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
        if (favourites.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFF151515)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Нет избранных курсов",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFF151515))
            ) {
                items(favourites) { course ->
                    CourseItem(
                        course = course,
                        onFavoriteClick = { viewModel.toggleFavorite(course) }
                    )
                }
            }
        }
    }
}