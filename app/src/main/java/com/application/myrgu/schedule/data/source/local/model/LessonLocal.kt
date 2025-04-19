package com.application.myrgu.schedule.data.source.local.model

import kotlinx.serialization.Serializable

@Serializable
data class LessonLocal(
    val startTime: String,
    val endTime: String,
    val type: String?,
    val name: String,
    val classroom: String?,
    val teacherOrGroups: String?,
)