package com.application.myrgu.core.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorRemote(

    @SerialName("error_message")
    val message: String,
)
