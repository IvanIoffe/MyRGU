package com.application.myrgu.schedule.utils

import android.content.Context
import com.application.myrgu.R
import com.application.myrgu.schedule.domain.model.Lesson
import com.application.myrgu.schedule.domain.model.ScheduleType

fun Lesson.getDisplayType(context: Context): String {
    return type ?: context.getString(R.string.no_lesson_type)
}

fun Lesson.getDisplayClassroom(context: Context): String {
    return classroom ?: context.getString(R.string.no_classroom)
}

fun Lesson.getDisplayTeacherOrGroups(context: Context, scheduleType: ScheduleType?): String {
    return groups ?: teacher
        ?: when (scheduleType) {
            ScheduleType.GROUP -> context.getString(R.string.no_teacher)
            ScheduleType.TEACHER -> context.getString(R.string.no_groups)
            else -> context.getString(R.string.no_data)
        }
}