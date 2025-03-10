package com.application.myrgu.auth.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseRemote(

    @SerialName("id")
    val id: Int,

    @SerialName("display_name")
    val displayName: String,
)