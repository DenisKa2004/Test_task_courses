package com.example.test_task_courses.uiActivity.mainScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test_task_courses.room.CourseEntity


@Composable
fun CourseItem(
    course: CourseEntity,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = course.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(onClick = onFavoriteClick) {
                    if (course.hasLike) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Избранное",
                            tint = Color(0xFF12B956)
                        )
                    } else {
                        Icon(
                            Icons.Outlined.FavoriteBorder,
                            contentDescription = "В избранные",
                            tint = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = course.text,
                color = Color(0xFFCCCCCC),
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "₽ ${course.price}",
                    color = Color(0xFF12B956),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "★ ${course.rate}",
                    color = Color(0xFFFFD700),
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Старт: ${course.startDate}",
                    color = Color(0xFF888888),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Опубликовано: ${course.publishDate}",
                    color = Color(0xFF888888),
                    fontSize = 12.sp
                )
            }
        }
    }
}