package com.application.myrgu.schedule.presentation.weekCalendar

import java.time.LocalDate

interface ChangeDateListener {
    fun onChanged(newDate: LocalDate, oldDate: LocalDate)
}