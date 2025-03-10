package com.application.myrgu.schedule.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.application.myrgu.schedule.data.source.local.model.ScheduleLocal
import com.application.myrgu.schedule.data.source.local.model.ScheduleVersionLocal

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM Schedule")
    suspend fun getSchedule(): ScheduleLocal

    @Insert
    suspend fun saveSchedule(schedule: ScheduleLocal)

    @Query("SELECT scheduleVersion FROM Schedule WHERE scheduleKey = :scheduleKey")
    suspend fun getScheduleVersion(scheduleKey: String): ScheduleVersionLocal
}