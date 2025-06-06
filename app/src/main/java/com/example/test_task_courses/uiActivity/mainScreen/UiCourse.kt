package com.example.test_task_courses.uiActivity.mainScreen

import java.time.LocalDate

data class UiCourse(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: Double,
    val hasLike: Boolean,
    val publishDate: String,
    val formattedPublishDate: String,
    val parsedDate: LocalDate
)

