package com.example.test_task_courses.uiActivity.mainScreen


import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.test_task_courses.R
import com.example.test_task_courses.navigation_app.Screen
import com.example.test_task_courses.room.CourseEntity

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val courses by viewModel.courses.collectAsState()
    val sortDescending by viewModel.sortDescending.collectAsState()
    val sortDesc by viewModel.sortDescending.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = { Column(Modifier.fillMaxWidth().height(50.dp).background(Color(0xFF151515))){} },
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
                        unselectedIconColor = Color.White,
                        selectedTextColor = Color(0xFF12B956),
                        unselectedTextColor = Color.White,
                        selectedIndicatorColor = Color(0xFF12B956),
                        disabledIconColor = Color.White,
                        disabledTextColor = Color.White,
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
                        selectedIndicatorColor = Color(0xFF12B956),
                        disabledIconColor = Color.Transparent,
                        disabledTextColor = Color.Transparent,
                    ),

                    )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF151515))
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF151515))
        ) {
            if (courses.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF151515)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Загрузка...", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF151515))
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { viewModel.updateSearchQuery(it) },
                                shape = RoundedCornerShape(28.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp)
                                    .background(Color(0xFF32333A), RoundedCornerShape(28.dp)),
                                textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, color = Color.White),
                                placeholder = { Text("Поиск", color = Color(0xFF888888)) },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(R.drawable.search),
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedContainerColor = Color(0xFF24252A),
                                    unfocusedContainerColor = Color(0xFF24252A),
                                    focusedIndicatorColor = Color(0xFF888888),
                                    unfocusedIndicatorColor = Color.Transparent,
                                    cursorColor = Color(0xFF12B956)
                                ),
                                singleLine = true
                            )

                            Spacer(modifier = Modifier.width(8.dp))
                            IconButton(
                                onClick = { },
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(Color(0xFF24252A), shape = RoundedCornerShape(28.dp))
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.filter),
                                    contentDescription = "Фильтр",
                                    tint = Color.White,
                                )
                            }
                        }
                    }
                    item {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 5.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = "По дате добавления",
                                modifier = Modifier.clickable { viewModel.toggleSort() },
                                color = if (sortDesc) Color.White else Color(0xFF12B956),
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                painter = painterResource(R.drawable.arrow_down_up),
                                contentDescription = "Сортировка",
                                tint = if (sortDescending) Color(0xFF12B956) else Color.White
                            )
                        }
                    }
                    items(courses) { course ->
                        CourseItem(
                            course = course,
                            onFavoriteClick = { viewModel.toggleFavorite(course) }
                        )
                    }
                }
            }
        }
    }
}
