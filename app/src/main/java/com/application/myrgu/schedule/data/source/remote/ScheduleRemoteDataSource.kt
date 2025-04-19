package com.application.myrgu.schedule.data.source.remote

import com.application.myrgu.core.data.Result
import com.application.myrgu.schedule.data.source.remote.model.ScheduleRemote
import com.application.myrgu.schedule.data.source.remote.model.ScheduleVersionRemote
import com.application.myrgu.schedule.data.source.remote.model.ScheduleVersionRequest
import com.application.myrgu.schedule.domain.model.ScheduleRequest
import kotlinx.coroutines.flow.Flow

interface ScheduleRemoteDataSource {

    fun getSchedule(scheduleRequest: ScheduleRequest): Flow<Result<ScheduleRemote>>

    suspend fun getScheduleVersion(scheduleVersionRequest: ScheduleVersionRequest): ScheduleVersionRemote?
}