package com.application.myrgu.schedule.data.di

import com.application.myrgu.schedule.data.repository.ScheduleRepositoryImpl
import com.application.myrgu.schedule.data.source.local.di.ScheduleLocalSourceModule
import com.application.myrgu.schedule.data.source.remote.di.ScheduleRemoteSourceModule
import com.application.myrgu.schedule.domain.repository.ScheduleRepository
import dagger.Binds
import dagger.Module

@Module(includes = [ScheduleRemoteSourceModule::class, ScheduleLocalSourceModule::class])
interface ScheduleModule {

    @Binds
    fun bindScheduleRepositoryImpl(
        scheduleRepositoryImpl: ScheduleRepositoryImpl,
    ): ScheduleRepository
}