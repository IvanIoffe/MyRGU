package com.application.myrgu.all_teachers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.myrgu.all_teachers.domain.model.AllTeachers
import com.application.myrgu.all_teachers.domain.model.Teacher
import com.application.myrgu.all_teachers.domain.usecase.GetAllTeachersUseCase
import com.application.myrgu.core.data.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AllTeachersViewModel @Inject constructor(
    private val getAllTeachersUseCase: GetAllTeachersUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<Result<AllTeachers>>(Result.Initial)

    private val filteredTeachers = MutableStateFlow<List<Teacher>?>(null)

    val state: StateFlow<Result<AllTeachers>> =
        combine(_state, filteredTeachers) { state, filteredList ->
            when (state) {
                is Result.Success -> {
                    Result.Success(AllTeachers(filteredList ?: state.data.items))
                }

                else -> state
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, Result.Initial)

    fun getAllTeachers() {
        getAllTeachersUseCase()
            .onEach {
                _state.value = it
            }
            .launchIn(viewModelScope)
    }

    fun filterList(query: String?) {
        val allTeachers = (_state.value as? Result.Success)?.data?.items
        filteredTeachers.value = if (query.isNullOrBlank()) {
            allTeachers
        } else {
            val filteredList = allTeachers?.filter { it.fullName.contains(query, true) }
            filteredList
        }
    }
}