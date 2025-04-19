package com.application.myrgu.schedule.data.source.remote.service

import com.application.myrgu.schedule.data.source.remote.model.ScheduleRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApiService {

    @GET("schedule_by_{endpoint}/{user_id}")
    suspend fun getSchedule(
        @Path("endpoint") scheduleEndpoint: String,
        @Path("user_id") userId: Int,
        @Query("first_date") dateOfMondayFirst: String,
        @Query("second_date") dateOfMondaySecond: String,
    ): Response<ScheduleRemote>
}