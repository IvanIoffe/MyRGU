package com.application.myrgu.schedule.data.source.remote.service

import com.application.myrgu.schedule.data.source.remote.model.ScheduleVersionRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleVersionApiService {

    @GET("scheduleversion")
    suspend fun getScheduleVersion(
        @Query("schedule_key") scheduleKey: String,
    ): Response<ScheduleVersionRemote>
}