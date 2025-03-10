package com.application.myrgu.all_teachers.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeacherRemote(

    @SerialName("id")
    val id: Int,

    @SerialName("imageUrl")
    val imageUrl: String = "https://thispersondoesnotexist.com/",

    @SerialName("name_full")
    val fullName: String,

    @SerialName("name_short")
    val shortName: String,

    @SerialName("post")
    val post: String,
)