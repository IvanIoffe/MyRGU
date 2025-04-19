package com.application.myrgu.schedule.data.source.local.di

import com.application.myrgu.core.data.source.local.db.AppDatabase
import com.application.myrgu.schedule.data.source.local.RoomScheduleLocalDataSource
import com.application.myrgu.schedule.data.source.local.ScheduleLocalDataSource
import com.application.myrgu.schedule.data.source.local.dao.ScheduleDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ScheduleLocalSourceModuleProvider::class, ScheduleLocalSourceModuleBinder::class])
class ScheduleLocalSourceModule

@Module
class ScheduleLocalSourceModuleProvider {

    @Singleton
    @Provides
    fun provideScheduleDao(appDatabase: AppDatabase): ScheduleDao {
        return appDatabase.scheduleDao()
    }
}

@Module
interface ScheduleLocalSourceModuleBinder {

    @Binds
    fun bindRoomScheduleLocalDataSource(
        roomScheduleLocalDataSource: RoomScheduleLocalDataSource,
    ): ScheduleLocalDataSource
}