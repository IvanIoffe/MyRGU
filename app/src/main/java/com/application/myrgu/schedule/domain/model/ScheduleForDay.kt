package com.application.myrgu.schedule.domain.model

data class ScheduleForDay(
    val dayOfWeek: String,
    val scheduleForDay: List<Lesson>,
)
