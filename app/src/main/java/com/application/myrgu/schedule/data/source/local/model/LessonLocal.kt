package com.application.myrgu.schedule.data.source.local.model

data class LessonLocal(
    val startTime: String,
    val endTime: String,
    val type: String?,
    val name: String,
    val classroom: String?,
    val teacher: String?,
)