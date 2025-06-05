package com.example.test_task_courses.retrofit

import android.content.Context
import com.example.test_task_courses.room.CourseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCourseRepository(
        @ApplicationContext context: Context,
        courseDao: CourseDao
    ): CourseRepository {
        return CourseRepositoryImpl(context, courseDao)
    }
}
