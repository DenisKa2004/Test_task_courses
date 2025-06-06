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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: CourseRepository
) : ViewModel() {


    private val _coursesFlow: StateFlow<List<CourseEntity>> =
        repository.allCourses.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    private val _sortDescending = MutableStateFlow(false)
    val sortDescending: StateFlow<Boolean> = _sortDescending.asStateFlow()

    private val _dateFilter = MutableStateFlow<LocalDate?>(null)
    val dateFilter: StateFlow<LocalDate?> = _dateFilter.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()


    init {
        viewModelScope.launch {
            repository.refreshCourses()
        }
    }

    fun toggleSort() {
        _sortDescending.update { !it }
    }

    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun toggleFavorite(course: UiCourse) {
        viewModelScope.launch {
            repository.toggleFavorite(course.id, !course.hasLike)
        }
    }

    fun toggleDateFilter() {
        _dateFilter.update {
            if (it == null) LocalDate.of(2024, 1, 1)
            else null
        }
    }


    private fun formatPublishDate(dateString: String): String {
        return try {
            val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
            val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
            val date = LocalDate.parse(dateString, inputFormatter)
            val formatted = date.format(outputFormatter)
            formatted.replaceFirstChar { it.uppercaseChar() }
        } catch (e: Exception) {
            dateString
        }
    }

    val courses: StateFlow<List<UiCourse>> = combine(
        _coursesFlow,
        _sortDescending,
        _searchQuery,
        _dateFilter
    ) { list, sortDesc, query, dateFilter ->

        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())

        list.mapNotNull { course ->
            try {
                val parsedDate = LocalDate.parse(course.publishDate, inputFormatter)
                UiCourse(
                    id = course.id,
                    title = course.title,
                    text = course.text,
                    price = course.price,
                    rate = course.rate,
                    hasLike = course.hasLike,
                    publishDate = course.publishDate,
                    formattedPublishDate = formatPublishDate(course.publishDate),
                    parsedDate = parsedDate
                )
            } catch (e: Exception) {
                null
            }
        }.filter { course ->
            course.title.contains(query.trim(), ignoreCase = true) &&
                    (dateFilter == null || course.parsedDate.isAfter(dateFilter))
        }.let { filtered ->
            if (sortDesc) filtered.sortedByDescending { it.parsedDate }
            else filtered.sortedBy { it.parsedDate }
        }

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )


}