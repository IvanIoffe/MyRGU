package com.application.myrgu.schedule.data.repository

import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.mapper.mapToDomainModelFlow
import com.application.myrgu.schedule.data.mapper.toSchedule
import com.application.myrgu.schedule.data.mapper.toScheduleLocal
import com.application.myrgu.schedule.data.source.local.ScheduleLocalDataSource
import com.application.myrgu.schedule.data.source.local.model.ScheduleVersionLocal
import com.application.myrgu.schedule.data.source.remote.ScheduleRemoteDataSource
import com.application.myrgu.schedule.data.source.remote.model.ScheduleVersionRemote
import com.application.myrgu.schedule.data.source.remote.model.ScheduleVersionRequest
import com.application.myrgu.schedule.domain.model.Schedule
import com.application.myrgu.schedule.domain.model.ScheduleRequest
import com.application.myrgu.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleRemoteDataSource: ScheduleRemoteDataSource,
    private val scheduleLocalDataSource: ScheduleLocalDataSource,
) : ScheduleRepository {

    override suspend fun getSchedule(scheduleRequest: ScheduleRequest): Flow<Result<Schedule>> {
        val scheduleVersionRemote = getScheduleVersionRemote(scheduleRequest)?.version
        val scheduleVersionLocal = getScheduleVersionLocal(scheduleRequest)?.version

        val scheduleKey = scheduleRequest.scheduleType.name + scheduleRequest.userId
        return when {
            scheduleVersionRemote != scheduleVersionLocal && scheduleVersionRemote != null -> {
                scheduleRemoteDataSource.getSchedule(scheduleRequest).mapToDomainModelFlow(
                    mapper = { scheduleRemote ->
                        scheduleRemote.toSchedule()
                    },
                    action = {
                        val scheduleLocal = it.toScheduleLocal(
                            scheduleKey = scheduleKey,
                            scheduleVersion = scheduleVersionRemote,
                        )
                        scheduleLocalDataSource.saveSchedule(scheduleLocal)
                    }
                )
            }

            scheduleVersionLocal != null -> {
                scheduleLocalDataSource.getSchedule(scheduleKey = scheduleKey).mapToDomainModelFlow(
                    mapper = { scheduleLocal ->
                        scheduleLocal.toSchedule()
                    }
                )
            }

            else -> {
                flow { emit(Result.Error("")) }
            }
        }
    }

    private suspend fun getScheduleVersionRemote(scheduleRequest: ScheduleRequest): ScheduleVersionRemote? {
        val scheduleVersionRequest = ScheduleVersionRequest(
            userId = scheduleRequest.userId,
            scheduleType = scheduleRequest.scheduleType,
        )
        return scheduleRemoteDataSource.getScheduleVersion(scheduleVersionRequest = scheduleVersionRequest)
    }

    private suspend fun getScheduleVersionLocal(scheduleRequest: ScheduleRequest): ScheduleVersionLocal? {
        val scheduleVersionRequest = ScheduleVersionRequest(
            userId = scheduleRequest.userId,
            scheduleType = scheduleRequest.scheduleType,
        )
        return scheduleLocalDataSource.getScheduleVersion(scheduleVersionRequest)
    }
}