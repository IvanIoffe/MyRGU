package com.application.myrgu.schedule.data.source.local.model

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleForWeekLocal(
    val dateOfMonday: String,
    val typeOfWeek: String,
    val schedule: List<ScheduleForDayLocal>,
)