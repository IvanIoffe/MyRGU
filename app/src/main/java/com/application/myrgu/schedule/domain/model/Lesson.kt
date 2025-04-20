package com.application.myrgu.schedule.domain.model

import java.time.LocalTime

data class Lesson(
    val startTime: LocalTime,
    val endTime: LocalTime,
    val type: String?,
    val name: String,
    val classroom: String?,
    val teacher: String?,
    val groups: String?,
)