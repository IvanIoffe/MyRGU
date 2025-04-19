package com.application.myrgu.schedule.data.source.remote.model

import com.application.myrgu.schedule.domain.model.ScheduleType

data class ScheduleVersionRequest(
    val userId: Int,
    val scheduleType: ScheduleType,
)