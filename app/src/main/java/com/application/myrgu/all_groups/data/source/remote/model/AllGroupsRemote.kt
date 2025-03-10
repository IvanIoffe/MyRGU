package com.application.myrgu.all_groups.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllGroupsRemote(

    @SerialName("groups")
    val items: List<GroupRemote>,
)
