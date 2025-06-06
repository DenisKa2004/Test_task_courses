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

class CourseRepositoryImpl @Inject constructor(
    private val apiService: CourseApiService,
    private val courseDao: CourseDao
) : CourseRepository {

    override val allCourses = courseDao.getAllCourses()

    override suspend fun refreshCourses() {
        // 1) получаем список текущих сущностей из БД
        val current = courseDao.getAllCoursesOnce()  // понадобится доп. метод в DAO

        // 2) грузим JSON
        val response = apiService.getCourses()

        // 3) для каждого dto смотрим, есть ли в current, и подставляем hasLike из БД
        val entities = response.courses.map { dto ->
            val existing = current.find { it.id == dto.id }
            CourseEntity(
                id = dto.id,
                title = dto.title,
                text = dto.text,
                price = dto.price,
                rate = dto.rate,
                startDate = dto.startDate,
                hasLike = existing?.hasLike ?: dto.hasLike,
                publishDate = dto.publishDate
            )
        }

        // 4) сохраняем — лайки, которые вы проставили, не будут перезаписаны обратно на false
        courseDao.insertCourses(entities)
    }

    override suspend fun toggleFavorite(id: Int, newHasLike: Boolean) {
        courseDao.updateHasLike(id, newHasLike)
    }
}


