package com.application.myrgu.schedule.data.source.local.model

import androidx.room.ColumnInfo

data class ScheduleVersionLocal(
    @ColumnInfo("schedule_version")
    val version: String,
)