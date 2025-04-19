package com.application.myrgu.schedule.data.mapper

import com.application.myrgu.schedule.data.source.local.model.ScheduleForDayLocal
import com.application.myrgu.schedule.data.source.local.model.ScheduleForWeekLocal
import com.application.myrgu.schedule.data.source.local.model.ScheduleLocal
import com.application.myrgu.schedule.data.source.remote.model.ScheduleRemote
import com.application.myrgu.schedule.domain.model.Schedule
import com.application.myrgu.schedule.domain.model.ScheduleForDay
import com.application.myrgu.schedule.domain.model.ScheduleForWeek
import com.application.myrgu.schedule.domain.model.ScheduleType
import com.application.myrgu.schedule.utils.dateTimeFormatter
import java.time.LocalDate

fun ScheduleRemote.toSchedule(): Schedule {
    val groupedByWeek = schedule.groupBy { it.dateOfMonday }

    val weeks = groupedByWeek.map { (dateOfMonday, lessonsForWeek) ->
        val groupedByDayOfWeek = lessonsForWeek.groupBy { it.dayOfWeek }

        val days = groupedByDayOfWeek.map { (dayOfWeek, lessonsRemote) ->
            ScheduleForDay(
                dayOfWeek = dayOfWeek,
                scheduleForDay = lessonsRemote
                    .map { lessonRemote -> lessonRemote.toLesson() }
                    .sortedBy { it.startTime.hour },
            )
        }

        ScheduleForWeek(
            dateOfMonday = LocalDate.parse(dateOfMonday, dateTimeFormatter),
            typeOfWeek = lessonsForWeek.first().typeOfWeek,
            schedule = days,
        )
    }

    return Schedule(items = weeks)
}

fun ScheduleRemote.toScheduleLocal(
    scheduleKey: String,
    scheduleVersion: String,
): ScheduleLocal {
    val groupedByWeek = schedule.groupBy { it.dateOfMonday }

    val weeks = groupedByWeek.map { (dateOfMonday, lessonsForWeek) ->
        val groupedByDayOfWeek = lessonsForWeek.groupBy { it.dayOfWeek }

        val days = groupedByDayOfWeek.map { (dayOfWeek, lessonsRemote) ->
            ScheduleForDayLocal(
                dayOfWeek = dayOfWeek,
                scheduleForDay = lessonsRemote.map { lessonRemote -> lessonRemote.toLessonLocal() },
            )
        }

        ScheduleForWeekLocal(
            dateOfMonday = dateOfMonday,
            typeOfWeek = lessonsForWeek.first().typeOfWeek,
            schedule = days,
        )
    }

    return ScheduleLocal(
        scheduleKey = scheduleKey,
        scheduleVersion = scheduleVersion,
        schedule = weeks,
    )
}

fun ScheduleLocal.toSchedule() =
    Schedule(
        items = schedule.map { scheduleForWeekLocal -> scheduleForWeekLocal.toScheduleForWeek() },
    )

fun ScheduleForWeekLocal.toScheduleForWeek() =
    ScheduleForWeek(
        dateOfMonday = LocalDate.parse(dateOfMonday, dateTimeFormatter),
        typeOfWeek = typeOfWeek,
        schedule = schedule.map { scheduleForDayLocal -> scheduleForDayLocal.toScheduleForDay() },
    )

fun ScheduleForDayLocal.toScheduleForDay() =
    ScheduleForDay(
        dayOfWeek = dayOfWeek,
        scheduleForDay = scheduleForDay
            .map { lessonLocal -> lessonLocal.toLesson() }
            .sortedBy { it.startTime.hour },
    )


fun ScheduleType.toPathParam(): String {
    return when (this) {
        ScheduleType.GROUP -> "group_id"
        ScheduleType.TEACHER -> "teacher_id"
    }
}