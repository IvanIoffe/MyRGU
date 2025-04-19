package com.application.myrgu.schedule.data.mapper

import com.application.myrgu.schedule.data.source.local.model.LessonLocal
import com.application.myrgu.schedule.data.source.remote.model.LessonRemote
import com.application.myrgu.schedule.domain.model.Lesson
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

fun LessonRemote.toLesson() =
    Lesson(
        startTime = LocalTime.parse(startTime, timeFormatter),
        endTime = LocalTime.parse(endTime, timeFormatter),
        type = type,
        name = name,
        classroom = classroom,
        teacherOrGroups = teacherOrGroups,
    )

fun LessonRemote.toLessonLocal() =
    LessonLocal(
        startTime = startTime,
        endTime = endTime,
        type = type,
        name = name,
        classroom = classroom,
        teacherOrGroups = teacherOrGroups,
    )

fun LessonLocal.toLesson() =
    Lesson(
        startTime = LocalTime.parse(startTime, timeFormatter),
        endTime = LocalTime.parse(endTime, timeFormatter),
        type = type,
        name = name,
        classroom = classroom,
        teacherOrGroups = teacherOrGroups,
    )