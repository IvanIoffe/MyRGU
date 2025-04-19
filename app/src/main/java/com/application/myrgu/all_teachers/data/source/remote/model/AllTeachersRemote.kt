package com.application.myrgu.all_teachers.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllTeachersRemote(

    @SerialName("teachers")
    val items: List<TeacherRemote>,
)