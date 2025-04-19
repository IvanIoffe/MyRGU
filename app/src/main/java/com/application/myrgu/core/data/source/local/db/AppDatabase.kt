package com.application.myrgu.core.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.application.myrgu.schedule.data.source.local.converter.ScheduleLocalConverter
import com.application.myrgu.schedule.data.source.local.dao.ScheduleDao
import com.application.myrgu.schedule.data.source.local.model.ScheduleLocal

@Database(entities = [ScheduleLocal::class], version = 1)
@TypeConverters(ScheduleLocalConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}