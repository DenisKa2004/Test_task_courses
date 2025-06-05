package com.example.test_task_courses.retrofit

import com.example.test_task_courses.retrofit.data_class.CoursesResponse
import retrofit2.http.GET

interface CourseApiService {
    @GET("courses.json")
    suspend fun getCourses(): CoursesResponse
}
