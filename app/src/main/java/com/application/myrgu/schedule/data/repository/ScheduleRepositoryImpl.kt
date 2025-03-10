package com.application.myrgu.schedule.data.repository

import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.mapper.mapToDomainModelFlow
import com.application.myrgu.schedule.data.mapper.toSchedule
import com.application.myrgu.schedule.data.source.local.ScheduleLocalDataSource
import com.application.myrgu.schedule.data.source.remote.ScheduleRemoteDataSource
import com.application.myrgu.schedule.domain.model.Schedule
import com.application.myrgu.schedule.domain.model.ScheduleRequest
import com.application.myrgu.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleRemoteDataSource: ScheduleRemoteDataSource,
    private val scheduleLocalDataSource: ScheduleLocalDataSource,
) : ScheduleRepository {

    override suspend fun getSchedule(scheduleRequest: ScheduleRequest): Flow<Result<Schedule>> {
        return scheduleRemoteDataSource.getSchedule(scheduleRequest).mapToDomainModelFlow(
            mapper = { scheduleRemote ->
                scheduleRemote.toSchedule()
            },
            action = {
                /*val scheduleLocal = it.toScheduleLocal(
                    scheduleKey = scheduleKey.value,
                    scheduleVersion = "12345"
                )
                scheduleLocalDataSource.saveSchedule(scheduleLocal)*/
            }
        )

        /*val scheduleVersionRemote = scheduleRemoteDataSource.getScheduleVersion(scheduleKey.value)?.version
        val scheduleVersionLocal = scheduleLocalDataSource.getScheduleVersion(scheduleKey.value)?.version

        return when {
            scheduleVersionRemote != scheduleVersionLocal && scheduleVersionRemote != null -> {
                scheduleRemoteDataSource.getSchedule(scheduleKey).mapToDomainModelFlow(
                    mapper = { scheduleRemote ->
                        scheduleRemote.toSchedule()
                    },
                    action = {
                        val scheduleLocal = it.toScheduleLocal(
                            scheduleKey = scheduleKey.value,
                            scheduleVersion = scheduleVersionRemote
                        )
                        scheduleLocalDataSource.saveSchedule(scheduleLocal)
                    }
                )
            }

            scheduleVersionLocal != null -> {
                scheduleLocalDataSource.getSchedule(scheduleKey.value).mapToDomainModelFlow(
                    mapper = { scheduleLocal ->
                        scheduleLocal.toSchedule()
                    }
                )
            }

            else -> {
                flow { emit(ApiResponse.Error("")) }
            }
        }*/
    }
}
