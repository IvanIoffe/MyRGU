package com.application.myrgu.all_groups.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.myrgu.all_groups.domain.model.AllGroups
import com.application.myrgu.all_groups.domain.model.Group
import com.application.myrgu.all_groups.domain.usecase.GetAllGroupsUseCase
import com.application.myrgu.core.data.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AllGroupsViewModel @Inject constructor(
    private val getAllGroupsUseCase: GetAllGroupsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<Result<AllGroups>>(Result.Initial)

    private val filteredGroups = MutableStateFlow<List<Group>?>(null)

    val state: StateFlow<Result<AllGroups>> =
        combine(_state, filteredGroups) { state, filteredList ->
            when (state) {
                is Result.Success -> {
                    Result.Success(AllGroups(filteredList ?: state.data.items))
                }

                else -> state
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, Result.Initial)

    fun getAllGroups() {
        getAllGroupsUseCase()
            .onEach {
                _state.value = it
            }
            .launchIn(viewModelScope)
    }

    fun filterList(query: String?) {
        val allGroups = (_state.value as? Result.Success)?.data?.items
        filteredGroups.value = if (query.isNullOrBlank()) {
            allGroups
        } else {
            val filteredList = allGroups?.filter { it.number.contains(query, true) }
            filteredList
        }
    }
}