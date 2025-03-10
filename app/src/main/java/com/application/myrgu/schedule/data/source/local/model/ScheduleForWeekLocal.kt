package com.application.myrgu.schedule.data.source.local.model

data class ScheduleForWeekLocal(
    val dateOfMonday: String,
    val typeOfWeek: String,
    val schedule: List<ScheduleForDayLocal>,
)