package com.example.test_task_courses.uiActivity.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_courses.retrofit.CourseRepository
import com.example.test_task_courses.retrofit.CourseRepositoryImpl
import com.example.test_task_courses.room.CourseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: CourseRepository // ← интерфейс, а не реализация
) : ViewModel() {

    private val _coursesFlow: StateFlow<List<CourseEntity>> =
        repository.allCourses.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _sortDescending = MutableStateFlow(false)
    val sortDescending: StateFlow<Boolean> = _sortDescending.asStateFlow()

    val courses: StateFlow<List<CourseEntity>> = combine(
        _coursesFlow,
        _sortDescending
    ) { list, sortDesc ->
        if (sortDesc) {
            list.sortedByDescending { it.publishDate }
        } else {
            list
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {
        // Загрузка курсов из локального JSON в БД
        viewModelScope.launch {
            repository.refreshCourses()
        }
    }

    fun toggleSort() {
        _sortDescending.update { !it }
    }

    fun toggleFavorite(course: CourseEntity) {
        viewModelScope.launch {
            repository.toggleFavorite(course.id, !course.hasLike)
        }
    }
}