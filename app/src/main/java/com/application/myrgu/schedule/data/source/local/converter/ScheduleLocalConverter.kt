package com.application.myrgu.schedule.data.source.local.converter

import androidx.room.TypeConverter
import com.application.myrgu.schedule.data.source.local.model.ScheduleForWeekLocal
import kotlinx.serialization.json.Json

class ScheduleLocalConverter {

    @TypeConverter
    fun fromScheduleForWeekLocalListToJson(scheduleForWeekLocalList: List<ScheduleForWeekLocal>) =
        Json.encodeToString(scheduleForWeekLocalList)

    @TypeConverter
    fun fromJsonToScheduleForWeekLocalList(value: String) =
        Json.decodeFromString<List<ScheduleForWeekLocal>>(value)
}