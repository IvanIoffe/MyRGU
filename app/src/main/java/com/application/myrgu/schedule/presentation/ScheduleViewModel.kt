package com.application.myrgu.schedule.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.myrgu.core.data.Result
import com.application.myrgu.schedule.domain.model.Lesson
import com.application.myrgu.schedule.domain.model.Schedule
import com.application.myrgu.schedule.domain.model.ScheduleForWeek
import com.application.myrgu.schedule.domain.model.ScheduleRequest
import com.application.myrgu.schedule.domain.usecase.GetScheduleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import java.time.LocalDate
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
    private val getScheduleUseCase: GetScheduleUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<Result<Schedule>>(Result.Initial)
    val state = _state.asStateFlow()

    private val _schedule = MutableLiveData<List<ScheduleForWeek>>()
    val schedule: LiveData<List<ScheduleForWeek>> = _schedule

    private val _scheduleForWeek = MutableLiveData<ScheduleForWeek>()
    val scheduleForWeek: LiveData<ScheduleForWeek> = _scheduleForWeek

    private val _scheduleForDay = MutableLiveData<List<Lesson>>()
    val scheduleForDay: LiveData<List<Lesson>> = _scheduleForDay

    fun getSchedule(scheduleRequest: ScheduleRequest) {
        viewModelScope.launch {
            yield()
            getScheduleUseCase(scheduleRequest)
                .onEach {
                    _state.value = it
                }
                .collect()
        }
    }

    fun saveSchedule(schedule: Schedule) {
        _schedule.value = schedule.items
    }

    fun updateScheduleForWeek(dateOfMonday: LocalDate) {
        _scheduleForWeek.value = _schedule.value?.find {
            dateOfMonday == it.dateOfMonday
        } ?: ScheduleForWeek(LocalDate.now(), "", listOf())
    }

    fun updateScheduleForDay(dayOfWeek: String) {
        _scheduleForDay.value = _scheduleForWeek.value?.schedule?.find {
            it.dayOfWeek.lowercase() == dayOfWeek.lowercase()
        }?.scheduleForDay ?: listOf()
    }
}