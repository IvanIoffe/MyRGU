package com.application.myrgu.schedule.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleVersionRemote(

    @SerialName("version")
    val version: String,
)
