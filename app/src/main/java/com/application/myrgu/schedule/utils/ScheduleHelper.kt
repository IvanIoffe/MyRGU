package com.application.myrgu.schedule.utils

import android.content.Context
import android.os.Bundle
import com.application.myrgu.R
import com.application.myrgu.auth.domain.model.UserAuthData
import com.application.myrgu.core.utils.Constants
import com.application.myrgu.schedule.domain.model.ScheduleRequest
import com.application.myrgu.schedule.domain.model.ScheduleType
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")

private val currentDate = LocalDate.now()

val dateOfMondayFirst: LocalDate = currentDate.with(DayOfWeek.MONDAY)
val dateOfMondaySecond: LocalDate = dateOfMondayFirst.plusDays(7)

var selectedDate: LocalDate =
    if (currentDate in dateOfMondayFirst..dateOfMondaySecond.with(DayOfWeek.SUNDAY))
        LocalDate.now()
    else
        dateOfMondayFirst

fun getReasonEmptySchedule(context: Context): String {
    return if (selectedDate.dayOfWeek != DayOfWeek.SUNDAY)
        context.getString(R.string.no_schedule_this_day)
    else
        context.getString(R.string.weekend)
}

fun createScheduleRequest(arguments: Bundle?, userAuthData: UserAuthData): ScheduleRequest {
    val userId = arguments?.getInt(Constants.USER_ID) ?: userAuthData.id
    val scheduleType = ScheduleType.valueOf(
        arguments?.getString(Constants.SCHEDULE_TYPE)
            ?: ScheduleType.getScheduleTypeName(userRole = userAuthData.role)
    )

    return ScheduleRequest(
        userId = userId,
        scheduleType = scheduleType,
        dateOfMondayFirst = dateOfMondayFirst,
        dateOfMondaySecond = dateOfMondaySecond,
    )
}