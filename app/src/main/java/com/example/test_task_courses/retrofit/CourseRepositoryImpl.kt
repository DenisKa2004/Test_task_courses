package com.example.test_task_courses.retrofit

import android.content.Context
import com.example.test_task_courses.retrofit.data_class.CoursesResponse
import com.example.test_task_courses.room.CourseDao
import com.example.test_task_courses.room.CourseEntity
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class CourseRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val courseDao: CourseDao
) : CourseRepository {

    override val allCourses: Flow<List<CourseEntity>> = courseDao.getAllCourses()

    override val favoriteCourses: Flow<List<CourseEntity>> =
        allCourses.map { it.filter { it.hasLike } }

    override suspend fun refreshCourses() {
        val json = context.assets.open("courses.json")
            .bufferedReader()
            .use { it.readText() }
       val response = Gson().fromJson(json, CoursesResponse::class.java)


        val entities = response.courses.map { dto ->
            CourseEntity(
                id = dto.id,
                title = dto.title,
                text = dto.text,
                price = dto.price,
                rate = dto.rate,
                startDate = dto.startDate,
                hasLike = dto.hasLike,
                publishDate = dto.publishDate
            )
        }
        courseDao.insertCourses(entities)
    }

    override suspend fun toggleFavorite(courseId: Int, newHasLike: Boolean) {
        courseDao.updateHasLike(courseId, newHasLike)
    }
}

