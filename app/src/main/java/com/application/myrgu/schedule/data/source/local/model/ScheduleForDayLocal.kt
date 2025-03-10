package com.application.myrgu.schedule.data.source.local.model

data class ScheduleForDayLocal(
    val dayOfWeek: String,
    val scheduleForDay: List<LessonLocal>,
)
