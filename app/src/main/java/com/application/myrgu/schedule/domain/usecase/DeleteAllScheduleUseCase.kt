package com.application.myrgu.schedule.domain.usecase

import com.application.myrgu.schedule.domain.repository.ScheduleRepository
import javax.inject.Inject

class DeleteAllScheduleUseCase @Inject constructor (
    private val scheduleRepository: ScheduleRepository,
) {
    suspend operator fun invoke() = scheduleRepository.deleteAllSchedule()
}