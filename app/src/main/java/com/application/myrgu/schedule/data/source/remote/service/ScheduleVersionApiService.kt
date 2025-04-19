package com.application.myrgu.schedule.data.source.remote.service

import com.application.myrgu.schedule.data.source.remote.model.ScheduleVersionRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleVersionApiService {

    @GET("schedule_version_by_{endpoint}/{user_id}")
    suspend fun getScheduleVersion(
        @Path("endpoint") scheduleVersionEndpoint: String,
        @Path("user_id") userId: Int,
    ): Response<ScheduleVersionRemote>
}