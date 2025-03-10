package com.application.myrgu.schedule.data.source.local.model

import androidx.room.ColumnInfo

data class ScheduleVersionLocal(
    @ColumnInfo("scheduleVersion")
    val version: String,
)
