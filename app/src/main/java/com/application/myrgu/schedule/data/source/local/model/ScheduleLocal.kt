package com.application.myrgu.schedule.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class ScheduleLocal(
    @PrimaryKey
    val scheduleKey: String,
    val scheduleVersion: String,
    val schedule: List<ScheduleForWeekLocal>,
)
