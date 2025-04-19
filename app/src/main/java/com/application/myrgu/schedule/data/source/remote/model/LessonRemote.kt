package com.application.myrgu.schedule.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonRemote(

    @SerialName("start_time")
    val startTime: String,

    @SerialName("end_time")
    val endTime: String,

    @SerialName("name_of_activity")
    val type: String?,

    @SerialName("subject")
    val name: String,

    @SerialName("number")
    val classroom: String?,

    @SerialName("teacher_or_groups")
    val teacherOrGroups: String?,

    @SerialName("name_of_day")
    val dayOfWeek: String,

    @SerialName("name_of_week")
    val typeOfWeek: String,

    @SerialName("date_of_monday")
    val dateOfMonday: String,
)