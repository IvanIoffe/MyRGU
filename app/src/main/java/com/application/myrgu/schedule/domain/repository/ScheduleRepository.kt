package com.application.myrgu.schedule.domain.repository

import com.application.myrgu.core.data.Result
import com.application.myrgu.schedule.domain.model.Schedule
import com.application.myrgu.schedule.domain.model.ScheduleRequest
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    fun getSchedule(scheduleRequest: ScheduleRequest): Flow<Result<Schedule>>
}