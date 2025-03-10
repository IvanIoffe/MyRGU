package com.application.myrgu.schedule.data.source.local.converter

import androidx.room.TypeConverter
import com.application.myrgu.schedule.data.source.local.model.ScheduleForDayLocal
import com.application.myrgu.schedule.data.source.local.model.ScheduleForWeekLocal
import kotlinx.serialization.json.Json

class ScheduleLocalConverter {

    @TypeConverter
    fun fromScheduleForWeekLocalListToJson(scheduleForWeekLocalList: List<ScheduleForWeekLocal>) =
        Json.encodeToString(scheduleForWeekLocalList)

    @TypeConverter
    fun fromJsonToScheduleForWeekLocalList(value: String) =
        Json.decodeFromString<List<ScheduleForWeekLocal>>(value)


    @TypeConverter
    fun fromScheduleForDayLocalListToJson(scheduleForDayLocalList: List<ScheduleForDayLocal>) =
        Json.encodeToString(scheduleForDayLocalList)

    @TypeConverter
    fun fromJsonToScheduleForDayLocalList(value: String) =
        Json.decodeFromString<List<ScheduleForDayLocal>>(value)
}