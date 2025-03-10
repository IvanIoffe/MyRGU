package com.application.myrgu.schedule.data.source.remote

import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.apiRemoteRequest
import com.application.myrgu.core.data.apiRemoteRequestFlow
import com.application.myrgu.schedule.data.mapper.toPathParam
import com.application.myrgu.schedule.data.source.remote.model.ScheduleRemote
import com.application.myrgu.schedule.data.source.remote.model.ScheduleVersionRemote
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
        return apiRemoteRequestFlow {
            scheduleApiService.getSchedule(
                scheduleType = scheduleRequest.scheduleType.toPathParam(),
                userId = scheduleRequest.userId,
                dateOfMondayFirst = scheduleRequest.dateOfMondayFirst.format(dateTimeFormatter),
                dateOfMondaySecond = scheduleRequest.dateOfMondaySecond.format(dateTimeFormatter),
            )
        }
    }

    override suspend fun getScheduleVersion(scheduleKey: String): ScheduleVersionRemote? {
        return apiRemoteRequest {
            scheduleVersionApiService.getScheduleVersion(scheduleKey)
        }
    }
}