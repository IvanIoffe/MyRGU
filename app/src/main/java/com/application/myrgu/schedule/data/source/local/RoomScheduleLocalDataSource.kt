package com.application.myrgu.schedule.data.source.local

import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.localRequest
import com.application.myrgu.core.data.localRequestFlow
import com.application.myrgu.schedule.data.source.local.dao.ScheduleDao
import com.application.myrgu.schedule.data.source.local.model.ScheduleLocal
import com.application.myrgu.schedule.data.source.local.model.ScheduleVersionLocal
import com.application.myrgu.schedule.data.source.remote.model.ScheduleVersionRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomScheduleLocalDataSource @Inject constructor(
    private val scheduleDao: ScheduleDao,
) : ScheduleLocalDataSource {

    override fun getSchedule(scheduleKey: String): Flow<Result<ScheduleLocal>> {
        return localRequestFlow {
            scheduleDao.getSchedule(scheduleKey)
        }
    }

    override suspend fun saveSchedule(scheduleLocal: ScheduleLocal) {
        scheduleDao.saveSchedule(scheduleLocal)
    }

    override suspend fun deleteAllSchedule() {
        scheduleDao.deleteAllSchedule()
    }

    override suspend fun getScheduleVersion(scheduleVersionRequest: ScheduleVersionRequest): ScheduleVersionLocal? {
        return localRequest {
            scheduleDao.getScheduleVersion(
                scheduleKey = scheduleVersionRequest.scheduleType.name + scheduleVersionRequest.userId,
            )
        }
    }
}