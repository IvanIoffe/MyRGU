package com.application.myrgu.schedule.presentation.weekCalendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.DayOfWeek
import javax.inject.Inject

class DayOfWeekViewModel @Inject constructor(): ViewModel() {
    private val _dayOfWeek = MutableLiveData<DayOfWeek>()
    val dayOfWeek: LiveData<DayOfWeek> = _dayOfWeek

    fun updateDayOfWeek(newDayOfWeek: DayOfWeek) {
        _dayOfWeek.value = newDayOfWeek
    }
}