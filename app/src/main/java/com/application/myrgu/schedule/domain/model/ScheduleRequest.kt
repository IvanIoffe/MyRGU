package com.application.myrgu.schedule.domain.model

import com.application.myrgu.auth.presentation.UserRole
import java.time.LocalDate

data class ScheduleRequest(
    val userId: Int,
    val scheduleType: ScheduleType,
    val dateOfMondayFirst: LocalDate,
    val dateOfMondaySecond: LocalDate,
)

enum class ScheduleType {
    GROUP,
    TEACHER;

    companion object {
        fun getScheduleTypeName(userRole: UserRole): String {
            return if (userRole == UserRole.GROUP) GROUP.name
            else TEACHER.name
        }
    }
}