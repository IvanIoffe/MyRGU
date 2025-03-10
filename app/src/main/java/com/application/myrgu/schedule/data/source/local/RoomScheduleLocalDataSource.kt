package com.application.myrgu.schedule.data.source.local

import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.localRequest
import com.application.myrgu.core.data.localRequestFlow
import com.application.myrgu.schedule.data.source.local.dao.ScheduleDao
import com.application.myrgu.schedule.data.source.local.model.ScheduleLocal
import com.application.myrgu.schedule.data.source.local.model.ScheduleVersionLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomScheduleLocalDataSource @Inject constructor(
    private val scheduleDao: ScheduleDao,
) : ScheduleLocalDataSource {

    override fun getSchedule(scheduleKey: String): Flow<Result<ScheduleLocal>> {
        return localRequestFlow {
            scheduleDao.getSchedule()
        }
    }

    override suspend fun saveSchedule(scheduleLocal: ScheduleLocal) {
        scheduleDao.saveSchedule(scheduleLocal)
    }

    override suspend fun getScheduleVersion(scheduleKey: String): ScheduleVersionLocal? {
        return localRequest {
            scheduleDao.getScheduleVersion(scheduleKey)
        }
    }
}