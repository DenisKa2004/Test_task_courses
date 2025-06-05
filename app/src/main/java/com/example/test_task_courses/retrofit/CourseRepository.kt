package com.example.test_task_courses.retrofit

import com.example.test_task_courses.retrofit.data_class.CourseDto
import com.example.test_task_courses.room.CourseEntity
import kotlinx.coroutines.flow.Flow


interface CourseRepository {
    val allCourses: Flow<List<CourseEntity>>
    val favoriteCourses: Flow<List<CourseEntity>>
    suspend fun refreshCourses()
    suspend fun toggleFavorite(courseId: Int, newHasLike: Boolean)
}
