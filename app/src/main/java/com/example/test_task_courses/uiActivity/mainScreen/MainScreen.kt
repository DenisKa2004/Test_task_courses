package com.example.test_task_courses.uiActivity.mainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val courses by viewModel.courses.collectAsState()
    val sortDescending by viewModel.sortDescending.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Курсы", fontSize = 20.sp, color = Color.White) },
                actions = {
                    IconButton(onClick = { viewModel.toggleSort() }) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "Сортировка",
                            tint = if (sortDescending) Color(0xFF12B956) else Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF121212)
                )
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) { paddingValues ->
        if (courses.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Загрузка...", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFF121212))
            ) {
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