package com.application.myrgu.schedule.domain.usecase

import com.application.myrgu.core.data.Result
import com.application.myrgu.schedule.domain.model.Schedule
import com.application.myrgu.schedule.domain.model.ScheduleRequest
import com.application.myrgu.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    operator fun invoke(scheduleRequest: ScheduleRequest): Flow<Result<Schedule>> =
        scheduleRepository.getSchedule(scheduleRequest)
}