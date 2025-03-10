package com.application.myrgu.schedule.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleRemote(

    @SerialName("schedule")
    val schedule: List<LessonRemote>,
)