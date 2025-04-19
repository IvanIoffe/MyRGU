package com.application.myrgu.all_groups.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupRemote(

    @SerialName("id")
    val id: Int,

    @SerialName("group_number")
    val number: String,
)