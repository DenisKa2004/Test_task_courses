package com.example.test_task_courses.room

import android.content.Context
import androidx.room.Room
import com.example.test_task_courses.room.AppDatabase
import com.example.test_task_courses.room.CourseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "courses_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCourseDao(appDatabase: AppDatabase): CourseDao {
        return appDatabase.courseDao()
    }
}
