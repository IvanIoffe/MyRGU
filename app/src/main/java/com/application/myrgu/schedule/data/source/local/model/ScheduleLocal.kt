package com.application.myrgu.schedule.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class ScheduleLocal(
    @PrimaryKey
    @ColumnInfo("schedule_key")
    val scheduleKey: String,

    @ColumnInfo("schedule_version")
    val scheduleVersion: String,

    @ColumnInfo("schedule")
    val schedule: List<ScheduleForWeekLocal>,
)