package com.application.myrgu.schedule.domain.model

import java.time.LocalDate

data class ScheduleForWeek(
    val dateOfMonday: LocalDate,
    val typeOfWeek: String,
    val schedule: List<ScheduleForDay>,
)