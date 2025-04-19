package com.application.myrgu.schedule.data.source.local.model

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleForDayLocal(
    val dayOfWeek: String,
    val scheduleForDay: List<LessonLocal>,
)
