package com.application.myrgu.schedule.data.source.local

import com.application.myrgu.core.data.Result
import com.application.myrgu.schedule.data.source.local.model.ScheduleLocal
import com.application.myrgu.schedule.data.source.local.model.ScheduleVersionLocal
import kotlinx.coroutines.flow.Flow

interface ScheduleLocalDataSource {
    fun getSchedule(scheduleKey: String): Flow<Result<ScheduleLocal>>

    suspend fun saveSchedule(scheduleLocal: ScheduleLocal)

    suspend fun getScheduleVersion(scheduleKey: String): ScheduleVersionLocal?
}