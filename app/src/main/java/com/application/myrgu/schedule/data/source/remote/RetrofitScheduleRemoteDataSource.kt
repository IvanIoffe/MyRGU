package com.application.myrgu.schedule.data.source.remote

import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.remoteRequest
import com.application.myrgu.core.data.remoteRequestFlow
import com.application.myrgu.schedule.data.mapper.toPathParam
import com.application.myrgu.schedule.data.source.remote.model.ScheduleRemote
import com.application.myrgu.schedule.data.source.remote.model.ScheduleVersionRemote
import com.application.myrgu.schedule.data.source.remote.model.ScheduleVersionRequest
import com.application.myrgu.schedule.data.source.remote.service.ScheduleApiService
import com.application.myrgu.schedule.data.source.remote.service.ScheduleVersionApiService
import com.application.myrgu.schedule.domain.model.ScheduleRequest
import com.application.myrgu.schedule.utils.dateTimeFormatter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrofitScheduleRemoteDataSource @Inject constructor(
    private val scheduleApiService: ScheduleApiService,
    private val scheduleVersionApiService: ScheduleVersionApiService,
) : ScheduleRemoteDataSource {

    override fun getSchedule(scheduleRequest: ScheduleRequest): Flow<Result<ScheduleRemote>> {
        return remoteRequestFlow {
            scheduleApiService.getSchedule(
                scheduleEndpoint = scheduleRequest.scheduleType.toPathParam(),
                userId = scheduleRequest.userId,
                dateOfMondayFirst = scheduleRequest.dateOfMondayFirst.format(dateTimeFormatter),
                dateOfMondaySecond = scheduleRequest.dateOfMondaySecond.format(dateTimeFormatter),
            )
        }
    }

    override suspend fun getScheduleVersion(scheduleVersionRequest: ScheduleVersionRequest): ScheduleVersionRemote? {
        return remoteRequest {
            scheduleVersionApiService.getScheduleVersion(
                scheduleVersionEndpoint = scheduleVersionRequest.scheduleType.toPathParam(),
                userId = scheduleVersionRequest.userId,
            )
        }
    }
}